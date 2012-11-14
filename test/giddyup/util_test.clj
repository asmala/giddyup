(ns giddyup.util-test
  (:use [clojure.test]
        [giddyup.util]
        [giddyup.test-support]))

(deftest test-anchor
  (testing "anchor"
    (is (= (anchor "my-id") "#my-id"))
    (is (= (anchor "#my-id") "#my-id"))))

(deftest test-css-classes
  (testing "css-classes"
    (is (= (css-classes "btn" ["success" "large"])
           "btn btn-success btn-large"))
    (is (= c css-classes))))

(deftest test-css-attr-map
  (testing "css-attr-map"
    (is (= (css-attr-map "btn" ["success" "large"])
           {:class "btn btn-success btn-large"}))
    (testing "with optional attrs"
      (is (= (css-attr-map "btn" ["success" "large"]
                           {:class "disabled" :disabled true})
             {:class "btn btn-success btn-large disabled" :disabled true})))
    (is (= cm css-attr-map))))