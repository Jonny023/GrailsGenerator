# Grails3 逆向工程

[参考地址](https://grails-plugins.github.io/grails-db-reverse-engineer/grails3v4/index.html)

> 项目描述
```
本项目是通过数据库表结构反向生成domain类，开发场景适用于项目重构且原项目非grails项目，避免重新设计表结构。
```


> 引入依赖，buildscript和主依赖dependencies中都要添加

```
 buildscript {
    ...
    dependencies {
       ...
       classpath 'org.grails.plugins:db-reverse-engineer:4.0.0'
       ...
    }
 }

 
 dependencies {
    ...
    compile 'org.grails.plugins:db-reverse-engineer:4.0.0'
    ...
 }
```

> 创建表
```
CREATE TABLE author (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  version bigint(20) NOT NULL,
  name varchar(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE book (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  version bigint(20) NOT NULL,
  title varchar(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE author_books (
  author_id bigint(20) NOT NULL,
  book_id bigint(20) NOT NULL,
  PRIMARY KEY (author_id,book_id),
  KEY FK24C812F6183CFE1B (book_id),
  KEY FK24C812F6DAE0A69B (author_id),
  CONSTRAINT FK24C812F6183CFE1B FOREIGN KEY (book_id) REFERENCES book (id),
  CONSTRAINT FK24C812F6DAE0A69B FOREIGN KEY (author_id) REFERENCES author (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE compos (
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  version bigint(20) NOT NULL,
  other varchar(255) NOT NULL,
  PRIMARY KEY (first_name,last_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE compound_unique (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  version bigint(20) NOT NULL,
  prop1 varchar(255) NOT NULL,
  prop2 varchar(255) NOT NULL,
  prop3 varchar(255) NOT NULL,
  prop4 varchar(255) NOT NULL,
  prop5 varchar(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY prop4 (prop4,prop3,prop2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE library (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  version bigint(20) NOT NULL,
  name varchar(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE visit (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  library_id bigint(20) NOT NULL,
  person varchar(255) NOT NULL,
  visit_date datetime NOT NULL,
  PRIMARY KEY (id),
  KEY FK6B04D4BE8E8E739 (library_id),
  CONSTRAINT FK6B04D4BE8E8E739 FOREIGN KEY (library_id) REFERENCES library (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE other (
  username varchar(255) NOT NULL,
  nonstandard_version_name bigint(20) NOT NULL,
  PRIMARY KEY (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  version bigint(20) NOT NULL,
  account_expired bit(1) NOT NULL,
  account_locked bit(1) NOT NULL,
  enabled bit(1) NOT NULL,
  password varchar(255) NOT NULL,
  password_expired bit(1) NOT NULL,
  username varchar(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE role (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  version bigint(20) NOT NULL,
  authority varchar(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY authority (authority)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user_role (
  role_id bigint(20) NOT NULL,
  user_id bigint(20) NOT NULL,
  date_updated datetime NOT NULL,
  PRIMARY KEY (role_id,user_id),
  KEY FK143BF46A667AF6FB (role_id),
  KEY FK143BF46ABA5BADB (user_id),
  CONSTRAINT FK143BF46A667AF6FB FOREIGN KEY (role_id) REFERENCES role (id),
  CONSTRAINT FK143BF46ABA5BADB FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE thing (
  thing_id bigint(20) NOT NULL AUTO_INCREMENT,
  version bigint(20) NOT NULL,
  email varchar(255) NOT NULL,
  float_value float NOT NULL,
  name varchar(123) DEFAULT NULL,
  PRIMARY KEY (thing_id),
  UNIQUE KEY email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

### 步骤说明
* 新建项目引入依赖
* 配置application.groovy
* 配置数据库
* 运行命令 db-reverse-engineer 或 dbReverseEngineer
