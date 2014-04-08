(ns giddyup.display-test
  (:use [clojure.test]
        [giddyup.display]
        [giddyup.test-support]))

(deftest test-accordion
  (testing "accordion"
    (matches-template? :accordion
                       (accordion
                        "accordion"
                        ["Bootstrap" "Boostrap is an HTML scaffold for web apps"]
                        ["Clojure" "Clojure is a powerful JVM language"]
                        ["Giddyup" "Giddyup brings Boostrap and Clojure together"]))))

(deftest test-carousel
  (testing "carousel"
    (matches-template? :carousel
                       (carousel
                        "carousel"
                        [[:img {:src "http://lorempixel.com/600/400/nature/"}]
                         "Nature"]
                        [[:img {:src "http://lorempixel.com/600/400/city/"}]
                         "City"]
                        [[:img {:src "http://lorempixel.com/600/400/technics/"}]
                         "Tech"]))))

(deftest test-media-object
  (testing "media-object"
    (let [img-src "http://lorempixel.com/80/80/abstract/"]
      (testing "string arguments"
        (matches-template? :media-object
                           (media-object
                            img-src
                            "On Apr 8, 2014, Anonymous wrote:"
                            [:p "This seems to work well enough."])))
      (testing "vector arguments"
        (matches-template? :media-object
                           (media-object
                            [:img.pull-left.media-object {:src img-src}]
                            "On Apr 8, 2014, Anonymous wrote:"
                            [:p "This seems to work well enough."]))))))
