1. Запуск БД:
```shell
docker-compose -f docker-compose-postgres.yaml up -d
liquibase --liquibase-schema-name=exchange_rate_db --schema-name=public --changeLogFile=db.changelog-master.yaml --url=jdbc:postgresql://localhost:5433/exchange_rate_db --username=exchange_rate_db_user --password=master update
```