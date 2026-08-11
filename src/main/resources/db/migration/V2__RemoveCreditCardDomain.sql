ALTER TABLE "transaction" DROP CONSTRAINT "check_transaction_not_empty";
ALTER TABLE "transaction" DROP CONSTRAINT "fk_statement";
ALTER TABLE "transaction" DROP COLUMN "statement_id";
DROP TABLE "statement";
DROP TABLE "credit_card";
ALTER TABLE "transaction" ADD CONSTRAINT "check_transaction_not_empty"
CHECK (
    payers_account_id IS NOT NULL OR receivers_account_id IS NOT NULL
    );