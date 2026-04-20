USE voting_db;

INSERT INTO vote_item (item_name) VALUES ('電腦'), ('滑鼠');

INSERT INTO vote_record (voter_name, item_id) VALUES
  ('Leo', 1), ('Sandy', 1), ('Sandy', 2), ('Randy', 2), ('RSY', 2);
