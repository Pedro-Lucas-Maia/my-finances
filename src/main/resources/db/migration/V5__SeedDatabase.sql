-- ==========================================
-- V5: Seed Database (Populating Initial Data)
-- ==========================================

-- 1. Seed Banks
-- Utilizando UUIDs fixos para facilitar os testes na API
INSERT INTO "bank" (id, name) VALUES
    ('11111111-1111-1111-1111-111111111111', 'Banco do Brasil'),
    ('22222222-2222-2222-2222-222222222222', 'Caixa Econômica Federal'),
    ('33333333-3333-3333-3333-333333333333', 'Nubank'),
    ('44444444-4444-4444-4444-444444444444', 'Itaú'),
    ('55555555-5555-5555-5555-555555555555', 'Bradesco'),
    ('66666666-6666-6666-6666-666666666666', 'Santander'),
    ('77777777-7777-7777-7777-777777777777', 'Banco Inter'),
    ('88888888-8888-8888-8888-888888888888', 'C6 Bank')
ON CONFLICT (id) DO NOTHING;

-- 2. Seed Account Categories
INSERT INTO "account_category" (id, name) VALUES
    ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Conta Corrente'),
    ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Conta Investimento'),
    ('cccccccc-cccc-cccc-cccc-cccccccccccc', 'Poupança'),
    ('dddddddd-dddd-dddd-dddd-dddddddddddd', 'Conta Conjunta'),
    ('eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee', 'Carteira (Dinheiro Físico)')
ON CONFLICT (id) DO NOTHING;

-- 3. Seed Transaction Categories
INSERT INTO "transaction_category" (id, name) VALUES
    ('10000000-0000-0000-0000-000000000001', 'Mercado e Alimentação'),
    ('10000000-0000-0000-0000-000000000002', 'Automóvel e Transporte'),
    ('10000000-0000-0000-0000-000000000003', 'Lazer e Entretenimento'),
    ('10000000-0000-0000-0000-000000000004', 'Saúde e Farmácia'),
    ('10000000-0000-0000-0000-000000000005', 'Educação e Cursos'),
    ('10000000-0000-0000-0000-000000000006', 'Moradia (Aluguel/Contas)'),
    ('10000000-0000-0000-0000-000000000007', 'Salário e Renda'),
    ('10000000-0000-0000-0000-000000000008', 'Investimentos'),
    ('10000000-0000-0000-0000-000000000009', 'Viagens'),
    ('10000000-0000-0000-0000-000000000010', 'Vestuário e Beleza')
ON CONFLICT (id) DO NOTHING;
