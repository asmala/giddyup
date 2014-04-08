(ns giddyup.modal
  "Bootstrap modal element generation."
  (:use [hiccup.def :only [defelem]]))

(defelem modal
  "Returns a modal element with `content`.

  ### Example

      (modal
       (modal-header \"Question for You\")
       [:div.modal-body
        [:p \"What does Marcellus Wallace look like?\"]]
       [:div.modal-footer
        (modal-dismiss-button {:class \"btn\"} \"Wha-wha...?\")])"
  [& content]
  [:div#modal.modal.fade.in {:role "dialog" :tabindex "-1" :aria-hidden "true"}
   [:div.modal-dialog
    [:div.modal-content
     content]]])

(defelem modal-dismiss-button
  "Returns a dismiss button for the modal.

  ### Example

      (modal-dismiss-link {:class \"btn\"} \"Wha-wha...?\")"
  [& content]
  [:button {:data-dismiss "modal" :type "button"} content])

(defelem modal-header
  "Returns a modal header element with the title `s`.

  ### Example

      (modal-header \"Question for You\")"
  [s]
  [:div.modal-header
   (modal-dismiss-button {:class "close" :aria-hidden "true"} "×")
   [:h4.modal-title s]])
