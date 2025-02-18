1. Запуск БД:
```shell
docker-compose -f docker-compose-postgres.yaml up -d
liquibase --liquibase-schema-name=user_credential_liquibase --schema-name=public --changeLogFile=db.changelog-master.yaml --url=jdbc:postgresql://localhost:5433/user_db --username=user --password=master update
```