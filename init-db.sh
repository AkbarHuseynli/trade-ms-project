set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE DATABASE db_product;
    CREATE DATABASE db_order;
    CREATE DATABASE db_payment;
EOSQL