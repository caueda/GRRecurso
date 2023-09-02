MySQL

Logar no MySQL (root sem senha -wampserver)
mysql -u root

Logar no MySQL (MySQL instalado separadamente, informar a senha)
mysql -u root -p 

Após estar logado como root:

create database grrecurso;
create user 'grrecurso'@'localhost' identified by 'welcome1';
grant all on grrecurso.* to 'grrecurso'@'localhost';

Fazer o import da Base (script grrecurso.sql)

Servidor de aplicação: Wilfly 9 ou 10.

Configurar o modulo JDBC para MySQL

Fazer o download do wildfly 10

criar uma pasta em $WILDFLY_HOME/modules/system/layers/base/com/mysql/main

Copiar o jar do driver do MySQL (deploy/mysql-connector-java-8.0.26.jar) para a pasta criada
assim como o arquivo module.xml (deploy/module.xml)

No arquivo standalone.xml,
adicionar em <datasources>...</datasource>

                <datasource jta="true" jndi-name="java:jboss/GRRecursoPool" pool-name="GRRecursoPool" enabled="true">
                    <connection-url>jdbc:mysql://localhost:3306/grrecurso</connection-url>
                    <driver>mysql</driver>
                    <security>
                        <user-name>grrecurso</user-name>
                        <password>welcome1</password>
                    </security>
                </datasource>

E em drivers, adicione:

                    <driver name="mysql" module="com.mysql">
                        <driver-class>com.mysql.cj.jdbc.Driver</driver-class>
                    </driver>

Criar o arquivo deploy/module.xml na pasta criada.

Configuração do pool de conexões (standalone.xml):

				<datasource jndi-name="java:jboss/GRRecursoPool" pool-name="GRRecursoPool" enabled="true" use-java-context="true">
                    <connection-url>jdbc:mysql://localhost:3306/grrecurso</connection-url>
                    <driver>mysql</driver>
                    <pool>
                        <min-pool-size>10</min-pool-size>
                        <max-pool-size>20</max-pool-size>
                        <prefill>true</prefill>
                    </pool>
                    <security>
                        <user-name>grrecurso</user-name>
                        <password>welcome1</password>
                    </security>
                </datasource>
                
Na aplicação, após levantar o servidor:
http://127.0.0.1:8080/GRRecurso
Usuário: developer@test.com
Senha: welcome1                