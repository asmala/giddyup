(ns giddyup.info-test
  (:use [clojure.test]
        [giddyup.info]
        [giddyup.test-support]))

(deftest test-alert
  (testing "alert"
    (let [result (alert "Login failed")]
      (is (valid-hiccup? result))
      (is (has-tag? result [:a {:data-dismiss "alert"}])))))

(deftest test-progress-bar
  (testing "progress-bar"
    (let [result (progress-bar 67)]
      (is (valid-hiccup? result))
      (is (has-tag? result [:* {:style "width: 67%;"}])))))