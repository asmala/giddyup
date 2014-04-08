(ns giddyup.modal-test
  (:use [clojure.test]
        [giddyup.modal]
        [giddyup.test-support]))

(deftest test-modal
  (testing "modal"
    (matches-template? :modal
                       (modal (modal-header "Question for You")
                              [:div.modal-body
                               [:p "What does Marcellus Wallace look like?"]]
                              [:div.modal-footer
                               (modal-dismiss-button {:class "btn"} "Wha-wha...?")]))))

(deftest test-modal-dismiss-button
  (testing "modal-dismiss-button"
    (matches-template? :modal-dismiss-button
                       (modal-dismiss-button {:class "btn"} "Wha-wha...?"))))

(deftest test-modal-header
  (testing "modal-header"
    (matches-template? :modal-header
                       (modal-header "Question for You"))))
