(ns ^{:doc "Bootstrap modal element generation."}
  giddyup.modal
  (:use [hiccup.def :only [defelem]])
  (:require [hiccup.element :as html]))



(defelem modal
  "Returns a modal element with `content`.

  ### Example

      (modal
       (modal-header \"Question for You\")
       [:div.modal-body
        [:p \"What does Marcellus Wallace look like?\"]]
       [:div.modal-footer
        (modal-dismiss-link {:class \"btn\"} \"Wha-wha...?\")])"
  [& content]
  [:div#modal.modal.hide.fade {:role "dialog" :tabindex "-1" :aria-hidden true}
   content])

(defelem modal-dismiss-link
  "Returns a dismiss link for the modal.

  ### Example

      (modal-dismiss-link {:class \"btn\"} \"Wha-wha...?\")"
  [& content]
  (html/link-to {:data-dismiss "modal" :aria-hidden true} "#" content))

(defelem modal-header
  "Returns a modal header element with the title `s`.

  ### Example

      (modal-header \"Question for You\")"
  [s]
  [:div.modal-header
   (modal-dismiss-link {:class "close"} "×")
   [:h3 s]])