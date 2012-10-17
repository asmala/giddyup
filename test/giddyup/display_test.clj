(ns giddyup.display-test
  (:use [clojure.test]
        [giddyup.display]
        [giddyup.test-support]
        [hiccup.util :only [to-uri]]))

(deftest test-accordion
  (testing "accordion"
    (let [result (accordion "myAccordion"
                            ["First Slide" [:p.first] [:p.second]]
                            ["Second Slide" [:p.third] [:p.fourth]])]
      (is (valid-hiccup? result))
      (is (has-tags? result
                     [:div.accordion {:id "myAccordion"}]
                     [:a {:data-parent "#myAccordion" :data-toggle "collapse"
                          :href (to-uri "#myAccordion-0")}]
                     [:a {:data-parent "#myAccordion" :data-toggle "collapse"
                          :href (to-uri "#myAccordion-1")}]
                     [:div#myAccordion-0.accordion-body.collapse.in]
                     [:div#myAccordion-1.accordion-body.collapse]
                     [:* [:p.first] [:p.second]]
                     [:* [:p.third] [:p.fourth]])))))

(deftest test-carousel
  (testing "carousel"
    (let [result (carousel "myCarousel" [:div#first] [:div#second])]
      (is (valid-hiccup? result))
      (is (has-tags? result
                     [:div.carousel.slide {:id "myCarousel"}]
                     [:a {:data-slide "prev" :href (to-uri "#myCarousel")}]
                     [:a {:data-slide "next" :href (to-uri "#myCarousel")}]
                     [:div.item.active [:div#first]]
                     [:div.item [:div#second]])))))