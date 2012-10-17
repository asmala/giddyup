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
    (let [result (css-classes "btn" ["success" "large"])]
      (is (= result "btn btn-success btn-large"))
      (is (= result (c "btn" ["success" "large"]))))))

(deftest test-css-attr-map
  (testing "css-attr-map"
    (let [result (css-attr-map "btn" ["success" "large"])]
      (is (= result {:class "btn btn-success btn-large"}))
      (is (= result (cm "btn" ["success" "large"]))))
    (testing "with optional attrs"
      (let [result (css-attr-map "btn" ["success" "large"]
                                 {:class "disabled" :disabled true})]
        (is (= result {:class "btn btn-success btn-large disabled"
                       :disabled true}))))))