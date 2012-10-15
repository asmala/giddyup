(ns giddyup.modal
  (:use [hiccup.def :only [defelem]])
  (:require [hiccup.element :as html]))

(defelem modal
  "Returns a modal element with `content`."
  [& content]
  [:div#modal.modal.hide.fade {:role "dialog" :tabindex "-1" :aria-hidden true}
   content])

(defelem modal-dismiss-link
  "Returns a dismiss link for the modal."
  [& content]
  (html/link-to {:data-dismiss "modal" :aria-hidden true} "#" content))

(defelem modal-header
  "Returns a modal header element with the title `s`."
  [s]
  [:div.modal-header
   (modal-dismiss-link {:class "close"} "×")
   [:h3 s]])