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