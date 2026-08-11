CREATE TABLE "account_category" (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(30) NOT NULL
);

CREATE TABLE "transaction_category" (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(30) NOT NULL
);

ALTER TABLE "transaction" DROP COLUMN "category";
ALTER TABLE "account" DROP COLUMN "category";

ALTER TABLE "transaction" ADD COLUMN "category_id" UUID NOT NULL DEFAULT gen_random_uuid();
ALTER TABLE "transaction" ADD CONSTRAINT "fk_transaction_category" FOREIGN KEY ("category_id") REFERENCES "transaction_category"(id);

ALTER TABLE "account" ADD COLUMN "category_id" UUID NOT NULL DEFAULT gen_random_uuid();
ALTER TABLE "account" ADD CONSTRAINT "fk_account_category" FOREIGN KEY ("category_id") REFERENCES "account_category"(id);