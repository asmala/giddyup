(ns giddyup.navigation-test
  (:use [clojure.test]
        [giddyup.navigation]
        [giddyup.test-support]))

(deftest test-collapse-toggle
  (testing "collapse-toggle"
    (matches-template? :collapse-toggle
                       (collapse-toggle "#giddyup-navbar"))))

(deftest test-navbar
  (testing "navbar"
    (matches-template? :navbar
                       (navbar
                        [:div.navbar-header
                         [:a.navbar-brand {:href "#"} "Giddyup"]]
                        [:ul.nav.navbar-nav
                         [:li [:a {:href "#giddyup-display"} "Display"]]
                         [:li [:a {:href "#giddyup-info"} "Info"]]
                         [:li [:a {:href "#giddyup-modal"} "Modal"]]]))))

(deftest test-dropdown-menu
  (testing "dropdown-menu"
    (matches-template? :dropdown-menu
                       (dropdown-menu
                        ["Open..." "#open"]
                        :divider
                        "Save file"
                        ["Save" "#save"]
                        ["Save As..." "#save-as"]
                        :divider
                        ["Quit" "#quit"]))))

(deftest test-pager
  (testing "pager"
    (matches-template? :pager
                       (pager "← Previous" [:a {:href "#next"} "Next →"]))))
