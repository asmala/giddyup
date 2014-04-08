(ns giddyup.info-test
  (:use [clojure.test]
        [giddyup.info]
        [giddyup.test-support]))

(deftest test-alert
  (testing "alert"
    (matches-template? :alert
                       (alert :danger "Login failed"))))

(deftest test-icon
  (testing "icon"
    (matches-template? :icon
                       (icon :pencil))))

(deftest test-progress-bar
  (testing "progress-bar"
    (matches-template? :progress-bar
                       (progress-bar 60))))
