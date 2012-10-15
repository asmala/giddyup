(ns giddyup.modal-test
  (:use [clojure.test]
        [giddyup.modal]
        [giddyup.test-support]
        [hiccup.util :only [to-uri]]))

(deftest test-modal
  (testing "modal"
    (let [result (modal [:p.first] [:p.second])]
      (is (valid-hiccup? result))
      (is (has-tags? result
                     [:div#modal]
                     [:p.first] [:p.second])))))

(deftest test-modal-dismiss-link
  (testing "modal-dismiss-link"
    (let [result (modal-dismiss-link "Close")]
      (is (valid-hiccup? result))
      (is (has-tag? result [:a {:data-dismiss "modal"}])))))

(deftest test-modal-header
  (testing "modal-header"
    (let [result (modal-header "Question for You")]
      (is (valid-hiccup? result))
      (is (has-tags? result
                     [:a {:data-dismiss "modal"}]
                     [:* "Question for You"])))))