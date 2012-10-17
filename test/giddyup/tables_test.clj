(ns giddyup.tables-test
  (:use [clojure.test]
        [giddyup.tables]
        [giddyup.test-support]
        [hiccup.core :only [html]]))

(deftest test-thead
  (testing "thead"
    (let [result (thead "Name" "Email")]
      (is (valid-hiccup? result))
      (is (= (html result)
             (html [:thead [:tr [:th "Name"] [:th "Email"]]]))))))

(deftest test-row
  (testing "row"
    (let [result (row "Joe" "joe@example.com")]
      (is (valid-hiccup? result))
      (is (= (html result)
             (html [:tr [:td "Joe"] [:td "joe@example.com"]]))))))

(deftest test-tbody
  (testing "tbody"
    (let [result (tbody ["Joe" "joe@example.com"]
                        ["Jenny" "jenny@example.com"])]
      (is (valid-hiccup? result))
      (is (= (html result)
             (html [:tbody
                    [:tr [:td "Joe"] [:td "joe@example.com"]]
                    [:tr [:td "Jenny"] [:td "jenny@example.com"]]]))))))

(deftest test-table
  (testing "table"
    (testing "without caption"
      (let [result (table ["Name" "Email"]
                          ["Joe" "joe@example.com"]
                          ["Jenny" "jenny@example.com"])]
        (is (valid-hiccup? result))
        (is (not (has-tag? result [:caption])))
        (is (has-tags? result
                       (thead "Name" "Email")
                       (tbody ["Joe" "joe@example.com"]
                              ["Jenny" "jenny@example.com"])))))
    (testing "with caption"
      (let [result (table "Contact Info"
                          ["Name" "Email"]
                          ["Joe" "joe@example.com"]
                          ["Jenny" "jenny@example.com"])]
        (is (valid-hiccup? result))
        (is (has-tags? result
                       [:caption "Contact Info"]
                       (thead "Name" "Email")
                       (tbody ["Joe" "joe@example.com"]
                              ["Jenny" "jenny@example.com"])))))))