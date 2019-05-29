INSERT INTO client
  (id, version, created_at, modified_at, client_id)
VALUES
  (1, 0, now(), now(), 51);

INSERT INTO buyer
  (id, version, created_at, modified_at, name, email, cpf)
VALUES
  (1, 0, now(), now(), 'Adriano Ribeiro', 'amo@rock.com', '12345678901');

INSERT INTO credit_card
  (id, version, created_at, modified_at, holder_name, number, expiration_date, cvv)
VALUES
  (1, 0, now(), now(), 'Dri Dri', '1234567890123456', '2020-12-01', '666');

INSERT INTO payment
  (id, version, created_at, modified_at, client_id, buyer_id, amount, status)
VALUES
  (1, 0, now(), now(), 1, 1, 123.45, 'REJECTED');

INSERT INTO credit_card_payment
  (id, version, created_at, modified_at, payment_id, credit_card_id)
VALUES
  (1, 0, now(), now(), 1, 1);