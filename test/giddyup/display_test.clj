(ns giddyup.display-test
  (:use [clojure.test]
        [giddyup.display]
        [giddyup.test-support]
        [hiccup.util :only [to-uri]])
  (:require [hiccup.element :as html]))

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

(deftest test-media-object
  (testing "media-object"
    (testing "string arguments"
      (let [result (media-object "/img/avatars/joe.png" "On Nov 1st, 2012, Joe said:"
                                 [:span.comment "Media objects are neat!"])]
        (is (valid-hiccup? result))
        (is (has-tags? result
                       [:div.media]
                       [:img {:src (to-uri "/img/avatars/joe.png")}]
                       [:* "On Nov 1st, 2012, Joe said:"]
                       [:span.comment "Media objects are neat!"]))))
    (testing "vector arguments"
      (let [result (media-object
                    (html/image {:class "pull-right media-object"} "/img/admin.png")
                    [:h4.media-heading.admin "On Nov 2nd, 2012, Admin said:"]
                    [:* "Yeah, they were added in Bootstrap 2.2.0."])]
        (is (valid-hiccup? result))
        (is (has-tags? result
                       [:div.media]
                       [:img {:src (to-uri "/img/admin.png")}]
                       [:* "On Nov 2nd, 2012, Admin said:"]
                       [:* "Yeah, they were added in Bootstrap 2.2.0."]))))))

(deftest test-thumbnails
  (testing "thumbnails"
    (let [result (thumbnails "span4"
                             ["tibet.png"]
                             ["potala.png" "Potala Palace"]
                             ["kailash.png" "Mount Kailash" "#kailash"])]
      (is (valid-hiccup? result))
      (is (has-tags? result
                     [:ul.thumbnails]
                     [:li.span4]
                     (html/image "tibet.png")
                     (html/image "potala.png" "Potala Palace")
                     (html/image "kailash.png" "Mount Kailash")
                     [:div.thumbnail]
                     [:a.thumbnail {:href (to-uri "#kailash")}])))))