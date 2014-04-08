(ns giddyup.tables-test
  (:use [clojure.test]
        [giddyup.tables]
        [giddyup.test-support]))

(deftest test-thead
  (testing "thead"
    (matches-template? :thead
                       (thead "Name" "Email"))))

(deftest test-row
  (testing "row"
    (matches-template? :row
                       (row "Joe" "joe@example.com"))))

(deftest test-tbody
  (testing "tbody"
    (matches-template? :tbody
                       (tbody ["Joe" "joe@example.com"]
                              ["Jill" "jill@example.com"]))))

(deftest test-table
  (testing "table"
    (testing "without caption"
      (matches-template? :table-without-caption
                         (table ["Name" "Email"]
                                ["Joe" "joe@example.com"]
                                ["Jill" "jill@example.com"])))
    (testing "with caption"
      (matches-template? :table-with-caption
                         (table "Contact Info"
                                ["Name" "Email"]
                                ["Joe" "joe@example.com"]
                                ["Jill" "jill@example.com"])))))
