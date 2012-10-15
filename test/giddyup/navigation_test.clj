(ns giddyup.navigation-test
  (:use [clojure.test]
        [giddyup.navigation]
        [giddyup.test-support]
        [hiccup.util :only [to-uri]])
  (:require [hiccup.element :as html]))

(deftest test-dropdown-menu
  (testing "dropdown-menu"
    (let [result (dropdown-menu ["item 1" "/link-1"] :divider ["item 2" "/link-2"]
                                ["item 3" [["item 3.1" "/link-3-1"]]])]
      (is (valid-hiccup? result))
      (is (has-tags? result
                     [:a {:href (to-uri "/link-1")}]
                     [:li.divider]
                     [:a {:href (to-uri "/link-2")}]
                     [:a {:href (to-uri "/link-3-1")}])))))

(deftest test-nav-menu
  (testing "nav-menu"
    (let [result (nav-menu ["item 1" "/link-1"] :divider ["item 2" "/link-2"]
                           ["item 3" [["item 3.1" [["Item 3.1.1" "/link-3-1-1"]]]]])]
      (is (valid-hiccup? result))
      (is (has-tags? result
                     [:a {:href (to-uri "/link-1")}]
                     [:li.divider-vertical]
                     [:a {:href (to-uri "/link-2")}]
                     [:a {:href (to-uri "/link-3-1-1")}])))))

(deftest test-page
  (testing "pager"
    (let [result (pager "Previous" (html/link-to "#next" "Next"))]
      (is (valid-hiccup? result))
      (is (has-tags? result
                     [:a {:href (to-uri "#next")}]
                     [:* "Previous"])))))