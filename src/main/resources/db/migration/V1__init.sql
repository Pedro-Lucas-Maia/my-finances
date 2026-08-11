CREATE TABLE IF NOT EXISTS "bank"
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS "account" (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    balance DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(50) NOT NULL,
    bank_id UUID NOT NULL ,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_bank FOREIGN KEY (bank_id) REFERENCES "bank"(id)
);

CREATE TABLE IF NOT EXISTS "credit_card"
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    bank_id UUID NOT NULL,
    card_limit DECIMAL(10, 2) NOT NULL,
    close_date INTEGER NOT NULL,
    due_date INTEGER NOT NULL,
    CONSTRAINT fk_bank FOREIGN KEY (bank_id) REFERENCES "bank"(id)
);

CREATE TABLE IF NOT EXISTS "statement" (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    credit_card_id UUID NOT NULL,
    status VARCHAR(50) NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    due_date DATE NOT NULL,
    close_date DATE NOT NULL,
    CONSTRAINT fk_credit_card FOREIGN KEY (credit_card_id) REFERENCES "credit_card"(id)
);

CREATE TABLE IF NOT EXISTS "transaction"
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    amount DECIMAL(10, 2) NOT NULL,
    category VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL,
    date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    payers_account_id UUID,
    receivers_account_id UUID,
    statement_id UUID,
    CONSTRAINT fk_payers_account FOREIGN KEY (payers_account_id) REFERENCES "account"(id),
    CONSTRAINT fk_receivers_account FOREIGN KEY (receivers_account_id) REFERENCES "account"(id),
    CONSTRAINT fk_statement FOREIGN KEY (statement_id) REFERENCES "statement"(id),
    CONSTRAINT check_transaction_not_empty CHECK (
        (payers_account_id IS NOT NULL OR receivers_account_id IS NOT NULL) OR
        (statement_id IS NOT NULL)
    ),
    CONSTRAINT check_transaction_not_same_account CHECK (
        payers_account_id IS NULL OR receivers_account_id IS NULL OR payers_account_id <> receivers_account_id
    )
);