(ns giddyup.forms-test
  (:use [clojure.test]
        [giddyup.forms]
        [giddyup.test-support]
        [hiccup.util :only [to-uri]]))

(deftest test-wrapper
  (testing "wrapper"
    (let [result (wrapper [:label] [:span.error] [:p.hint] [:input.email])]
      (is (valid-hiccup? result))
      (is (has-tags? result [:label] [:span.error] [:p.hint] [:input.email])))
    (testing "abbreviated version"
      (let [result (wrapper [:label] [:input.email])]
        (is (valid-hiccup? result))
        (is (has-tags? result [:label] [:input.email]))))))

(deftest test-actions
  (testing "actions"
    (let [result (actions [:a.back "Back"] [:a.forward "Forward"])]
      (is (valid-hiccup? result))
      (is (has-tags? result [:a.back] [:a.forward])))))

(deftest test-submit-button
  (testing "submit-button"
    (let [result (submit-button "Save changes")]
      (is (valid-hiccup? result))
      (is (has-tags? result [:input {:type "submit" :value "Save changes"}])))))

(deftest test-button-link-to
  (testing "button-link-to"
    (let [result (button-link-to "/" "Back")]
      (is (valid-hiccup? result))
      (is (has-tag? result [:a {:href (to-uri "/")}])))))

(deftest test-label
  (testing "label"
    (let [result (label "name" "Name")]
      (is (valid-hiccup? result))
      (is (has-tag? result [:label {:for "name"}])))))

(deftest test-error
  (testing "error"
    (let [result (error "Email required")]
      (is (valid-hiccup? result))
      (is (has-tag? result [:* "Email required"])))))

(deftest test-hint
  (testing "hint"
    (let [result (hint "Double-check your email")]
      (is (valid-hiccup? result))
      (is (has-tag? result [:* "Double-check your email"])))))

(deftest test-fieldset
  (testing "fieldset"
    (let [result (fieldset "Profile" [:p.m1 "Mock 1"] [:p.m2 "Mock 2"])]
      (is (valid-hiccup? result))
      (is (has-tags? result [:fieldset] [:legend "Profile"] [:p.m1] [:p.m2])))))

(deftest test-check-box
  (testing "check-box"
    (let [result (check-box :remember-me false)]
      (is (valid-hiccup? result))
      (is (has-tag? result [:input {:type "checkbox" :name "remember-me"}])))))

(deftest test-group-check-box
  (testing "group-check-box"
    (let [result (group-check-box :extras "cpu" "Faster CPU" false)]
      (is (valid-hiccup? result))
      (is (has-tag? result [:input {:type "checkbox" :name "extras" :value "cpu"}]))
      (is (has-tag? result [:label])))))

(deftest test-drop-down
  (testing "drop-down"
    (let [paint-options [["White" "wh"] ["Black" "bl"]]
          result (drop-down :paint paint-options "bl")]
      (is (valid-hiccup? result))
      (is (has-tag? result [:option {:value "wh"} "White"]))
      (is (has-tag? result [:option {:value "bl" :selected true} "Black"]))
      (is (has-tag? result [:select {:name "paint"}])))))

(deftest test-email-field
  (testing "email-field"
    (let [result (email-field :your-email)]
      (is (valid-hiccup? result))
      (is (has-tag? result [:input {:type "email" :name "your-email"}])))))

(deftest test-file-upload
  (testing "file-upload"
    (let [result (file-upload :avatar)]
      (is (valid-hiccup? result))
      (is (has-tag? result [:input {:type "file" :name "avatar"}])))))

(deftest test-password-field
  (testing "password-field"
    (let [result (password-field :api-key)]
      (is (valid-hiccup? result))
      (is (has-tag? result [:input {:type "password" :name "api-key"}])))))

(deftest test-group-radio-button
  (testing "group-radio-button"
    (let [result (group-radio-button :colors "bl" "Black" false)]
      (is (valid-hiccup? result))
      (is (has-tag? result [:input {:type "radio" :name "colors" :value "bl"}]))
      (is (has-tag? result [:label])))))

(deftest test-text-area
  (testing "text-area"
    (let [result (text-area :bio "It's a long story...")]
      (is (valid-hiccup? result))
      (is (has-tag? result [:textarea {:name "bio"} "It's a long story..."])))))

(deftest test-text-field
  (testing "text-field"
    (let [result (text-field :name "Joe")]
      (is (valid-hiccup? result))
      (is (has-tag? result [:input {:type "text" :name "name" :value "Joe"}])))))

(deftest test-hidden-field
  (testing "hidden-field"
    (let [result (hidden-field :redirect-url "example.com")]
      (is (valid-hiccup? result))
      (is (has-tag? result [:input {:type "hidden" :name "redirect-url"
                                    :value "example.com"}])))))