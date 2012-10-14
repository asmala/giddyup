(ns giddyup.util-test
  (:use [clojure.test]
        [giddyup.util]
        [giddyup.test-support]))

(deftest test-alert
  (testing "alert"
    (let [result (alert "Login failed")]
      (is (valid-hiccup? result))
      (is (has-tag? result [:a {:data-dismiss "alert"}])))))
