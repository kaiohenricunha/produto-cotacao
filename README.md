# Aplicação Spring

## Starting this application

### First and foremost: you should have Java, MySQL and Maven installed beforehand. Both projects share many characteristcs, there is a project responsible for the ``products`` and another for handling the ``quotations``. So, you should run both produto-cotacao and cotacao-produto because they interact with each other as microsservices. 

#### the projects work on the following ports:
```
http://localhost:8081/produtos
```

```
http://localhost:8082/cotacoes
```
## pom.xml

#### I opted to use Spring-Boot to generate the dependecies.

```
       <?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.12.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.infnet</groupId>
    <artifactId>tp3</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>tp3</name>
    <description>Demo project for Spring Boot</description>
    <properties>
        <java.version>1.8</java.version>
        <spring-cloud.version>Hoxton.SR11</spring-cloud.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-rest</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-aws</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-aws-jdbc</artifactId>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>

```

## Classes

### Application.java
#### This is the main class, which is executed when the application is run.
```
package com.infnet.at;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

```


### Cotacao.java
#### This is a domain class representing the quotations, it has only a price source(fonteCotacao), a price(valor) and a product(produto).

```
package com.infnet.at.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Cotacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fonteCotacao;
    private double valor;
    @ManyToOne
    private Produto produto;


    public Cotacao() {
    }

    public Cotacao(String fonteCotacao, double valor, Produto produto) {
        this.fonteCotacao = fonteCotacao;
        this.valor = valor;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFonteCotacao() {
        return fonteCotacao;
    }

    public void setFonteCotacao(String fonteCotacao) {
        this.fonteCotacao = fonteCotacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "Cotacao{" +
                "id=" + id +
                ", fonteCotacao='" + fonteCotacao + '\'' +
                ", valor=" + valor +
                ", produto=" + produto +
                '}';
    }
}
```

### Produto.java
#### This is a domain class representing the products, it has only a name(nome), an image URL(imagem) and List of quotations(cotacoes).
```
package com.infnet.at.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Produto {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String imagem;
    @OneToMany
    private List<Cotacao> cotacoes;

    public Produto() {
    }

    public Produto(String nome, String imagem, List<Cotacao> cotacoes) {
        this.nome = nome;
        this.imagem = imagem;
        this.cotacoes = cotacoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public List<Cotacao> getCotacoes() {
        return cotacoes;
    }

    public void setCotacoes(List<Cotacao> cotacoes) {
        this.cotacoes = cotacoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", imagem='" + imagem + '\'' +
                ", cotacoes=" + cotacoes +
                '}';
    }
}

```

### ProdutoController.java
#### This class is responsible for registering, editing, listing and deleting products.
```
package com.infnet.at.controllers;

import com.infnet.at.clients.AmazonClient;
import com.infnet.at.clients.CotacaoClient;
import com.infnet.at.models.Cotacao;
import com.infnet.at.models.Produto;
import com.infnet.at.repository.ProdutoRepository;
import com.infnet.at.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;
    @Autowired
    CotacaoClient cotacaoClient;
    @Autowired
    AmazonClient amazonClient;
    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping
    public List<Produto> getProdutos() {
        return produtoService.getProdutos();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Produto> getOne(@PathVariable Long id) {
        return produtoRepository.findById(id);
    }

    @PostMapping
    public Produto register(@RequestPart(value = "nome") String nome,
                            @RequestPart(value = "mfile") MultipartFile mfile) throws IOException {

        //captura a url da imagem e coloca numa string, que vai virar o atributo "imagem" do produto
        String imagemUrl = amazonClient.uploadFile(mfile);
        //transforma o MultiPartFile num File
        File file = amazonClient.convertMultiPartToFile(mfile);
        //envia o "file" para o S3
        amazonClient.uploadFileTos3bucket(imagemUrl, file);

        return produtoService.register(nome, imagemUrl);
    }

    @RequestMapping(value = "/{idProduto}", method = RequestMethod.PUT)
    public Produto editarProduto(@RequestPart("nome") String nome,
                              @RequestPart("imagem") MultipartFile imagem,
                              @PathVariable Long idProduto) throws IOException {

        Produto produto = produtoRepository.findProdutosById(idProduto);
        produto.setNome(nome);
        //captura a url da imagem e coloca numa string, que vai virar o atributo "imagem" do produto
        String imagemUrl = amazonClient.uploadFile(imagem);
        //transforma o MultiPartFile num File
        File file = amazonClient.convertMultiPartToFile(imagem);
        //envia o "file" para o S3
        amazonClient.uploadFileTos3bucket(imagemUrl, file);
        produto.setImagem(imagemUrl);
        return produtoService.edit(produto);
    }

    @PutMapping
    public void update() {
        //produtoService.update(id, produto);
    }

    @RequestMapping(value = "/{idProduto}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idProduto) {
        Produto produto = produtoRepository.findProdutosById(idProduto);
        produtoService.delete(produto);
    }

}

```

### CotacaoController.java
#### This class is responsible for registering, editing, listing and deleting quotations.
```
package com.infnet.at.controllers;

import com.infnet.at.client.ProdutoClient;
import com.infnet.at.models.Cotacao;
import com.infnet.at.models.Produto;
import com.infnet.at.repository.CotacaoRepository;
import com.infnet.at.service.CotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/cotacoes")
public class CotacaoController {

    @Autowired
    CotacaoService cotacaoService;
    @Autowired
    ProdutoClient produtoClient;
    @Autowired
    CotacaoRepository cotacaoRepository;

    @GetMapping
    public List<Cotacao> getCotacoes() {
        return cotacaoService.getCotacoes();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Cotacao> getOne(@PathVariable Long id) {
        return cotacaoRepository.findById(id);
    }

    @GetMapping(path = "/{produtoId}")
    public List<Cotacao> getCotacoesByProduto(@PathVariable Long produtoId) {
        Produto produto = produtoClient.buscarProdutoPorId(produtoId);
        return cotacaoService.getCotacoesByProduto(produto);
    }

    @PostMapping( "/{idProduto}")
    public Cotacao register(@RequestBody Cotacao cotacao,
                            @PathVariable Long idProduto) {

        Produto produto = produtoClient.buscarProdutoPorId(idProduto);
        cotacao.setProduto(produto);
        Cotacao cotacao1 = cotacaoService.register(cotacao);
        return cotacao1;
    }

    @PutMapping("/{idCotacao}")
    public Cotacao editarCotacao(@RequestPart(value = "fonteCotacao") String fonteCotacao,
                                 @RequestPart(value = "valor") String valor,
                                @PathVariable Long idCotacao) {
        Cotacao cotacao = cotacaoRepository.findCotacaoById(idCotacao);
        cotacao.setFonteCotacao(fonteCotacao);
        cotacao.setValor(valor);
        return cotacaoService.edit(cotacao);
    }

    @RequestMapping(value = "/{idCotacao}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idCotacao) {
        Cotacao cotacao = cotacaoRepository.findCotacaoById(idCotacao);
        cotacaoService.delete(cotacao);
    }

}


```

### BucketController.java
#### Class responsible for uploading and deleting files to and from AWS S3
```
package com.infnet.at.controllers;

import com.infnet.at.clients.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/storage/")
public class BucketController {

    @Autowired
    private AmazonClient amazonClient;

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
        return this.amazonClient.uploadFile(file);
    }

    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
    }
}
```

### AmazonClient.java
#### Here, the file sent via HTTP request is managed and transformed, for example, into an URL and a File.
```
package com.infnet.at.clients;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AmazonClient {

    private AmazonS3 s3client;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = new AmazonS3Client(credentials);
    }

    public File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace("", "_");
    }

    public void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(
                new PutObjectRequest(bucketName, fileName, file)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public String uploadFile(MultipartFile multipartFile) {

        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileUrl;
    }

    public String deleteFileFromS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        s3client.deleteObject(new DeleteObjectRequest(bucketName + "/", fileName));
        return "Successfully deleted";
    }
}

```

### CotacaoClient.java
#### This class interacts with produto-cotacao, returning a Cotacao to be set in a Produto.
```
package com.infnet.at.clients;

import com.infnet.at.models.Cotacao;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CotacaoClient {
    public Cotacao buscaCotacaoPorId(Long id) {
        RestTemplate template = new RestTemplate();
        return template.getForObject("http://localhost:8082/cotacoes/{id}", Cotacao.class, id);    }
}

```

### ProdutoClient.java
#### This class works the same way as CotacaoClient, but returns a Produto instead.

```
package com.infnet.at.client;

import com.infnet.at.models.Produto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProdutoClient {

    public Produto buscarProdutoPorId(Long id) {
        RestTemplate template = new RestTemplate();
        return template.getForObject("http://localhost:8081/produtos/{id}", Produto.class, id);
         }
}
```

### ProdutoRepository.java
#### This is the project's repository, where instances of Produto are sent to the database via Java Persistence API(JPA).

```
package com.infnet.at.repository;

import com.infnet.at.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    public Produto findProdutosById(Long id);
}
```

### CotacaoRepository.java
```
package com.infnet.at.repository;

import com.infnet.at.models.Cotacao;
import com.infnet.at.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {


    List<Cotacao> findCotacaoByProduto(Produto produto);

    Cotacao findCotacaoById(Long id);
}
```

### CotacaoService.java
#### This class, as the name says, is a service that interacts with the controllers to register, edit, list and delete instances of the objects.
```
package com.infnet.at.service;

import com.infnet.at.models.Cotacao;
import com.infnet.at.models.Produto;
import com.infnet.at.repository.CotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CotacaoService {

    @Autowired
    CotacaoRepository cotacaoRepository;

    public List<Cotacao> getCotacoes() {
        return cotacaoRepository.findAll();
    }

    public List<Cotacao> getCotacoesByProduto(Produto produto) {
        return cotacaoRepository.findCotacaoByProduto(produto);
    }

    public Cotacao edit(Cotacao cotacao) {
        cotacaoRepository.save(cotacao);
        return cotacao;
    }

    public Cotacao register(Cotacao cotacao) {
        return cotacaoRepository.save(cotacao);
    }

    public void update(Long id, Cotacao cotacao) {
        cotacao.setId(id);
        cotacaoRepository.save(cotacao);
    }

    public void delete(Cotacao cotacao) {
        cotacaoRepository.delete(cotacao);
    }
}
```

### ProdutoService.java
```
package com.infnet.at.services;

import com.infnet.at.models.Cotacao;
import com.infnet.at.models.Produto;
import com.infnet.at.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public List<Produto> getProdutos() {
        return produtoRepository.findAll();
    }

    public Produto register(String nome, String imagem) {

        return produtoRepository.save(new Produto(nome, imagem));
    }

    public void update(Long id, Produto produto) {
        produto.setId(id);
        produtoRepository.save(produto);
    }

    public Produto edit(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void delete(Produto produto) {
        produtoRepository.delete(produto);
    }
}
```

### application.properties
#### This is a configuration class. You have to include your own username, password a databaseName defined during the database creation on AWS RDS console
```
cloud.aws.credentials.use-default-aws-credentials-chain=true
cloud.aws.region.useDefaultAwsRegionChain=true
cloud.aws.stack.auto=false
logging.level.com.amazonaws.util.EC2MetadataUtils=error
logging.level.com.amazonaws.internal.InstanceMetadataServiceResourceFetcher=error
cloud.aws.rds.infnet-tp3.username=
cloud.aws.rds.infnet-tp3.password=
cloud.aws.rds.infnet-tp3.databaseName=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect
server.port=8081

```

### application.yml
#### Here, you have to configure your projects with AWS S3.
```
amazonProperties:
  endpointUrl:
  accessKey:
  secretKey:
  bucketName:
```