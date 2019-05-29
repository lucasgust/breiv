INSERT INTO client
  (id, version, created_at, modified_at, client_id)
VALUES
  (1, 0, now(), now(), 51);

INSERT INTO buyer
  (id, version, created_at, modified_at, name, email, cpf)
VALUES
  (1, 0, now(), now(), 'Adriano Ribeiro', 'amo@rock.com', '12345678901');

INSERT INTO payment
  (id, version, created_at, modified_at, client_id, buyer_id, amount, status)
VALUES
  (1, 0, now(), now(), 1, 1, 123.45, 'APPROVED');

INSERT INTO bank_slip_payment
  (id, version, created_at, modified_at, payment_id, number)
VALUES
  (1, 0, now(), now(), 1, '1234567890');