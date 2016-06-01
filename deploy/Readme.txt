Servidor de aplicação: Wilfly 9.

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