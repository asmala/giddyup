Giddyup: Bootstrap for Hiccup [![Build Status](https://secure.travis-ci.org/asmala/giddyup.png?branch=master)](http://travis-ci.org/asmala/giddyup)
=============================

A library of [Bootstrap](http://getbootstrap.com/) helpers for
[Hiccup](https://github.com/weavejester/hiccup/).


## Installation

Add the following to your `project.clj`:

```clojure
[giddyup "0.5.0"]
```

For other options, please refer to the library
[Clojars page](https://clojars.org/giddyup).


## Documentation

You can find examples below and for more extensive documentation you
can refer to the [API docs](http://asmala.github.com/giddyup).


## Forms

Easily create Bootstrap styled forms:

```clojure
(fieldset "New account"
          (wrapper
           (label :email "Email")
           nil
           (hint "Double-check your email address")
           (email-field {:placeholder "joe@example.com"} :email))
          (wrapper
           (label :password "Passoword")
           (password-field :password)))

(actions
 (button-link-to "/" "Cancel")
 (submit-button "Sign up"))
```


## Alerts and progress bars

```clojure
(alert "Logged in")

(alert {:class "alert alert-block alert-error"}
       [:h4 "Login failed"]
       [:p "Please check your username and password."])

(progress-bar 60)
```


## Navigation menus

```clojure
(nav-menu
 ["Journal Entries" "/journal-entries"]
 :divider
 ["Media" [["Add new" "/media/new"]
           :divider
           ["Photos" "/media/photos"]
           ["Video" "/media/video"]]])

(breadcrumbs
 (link-to "#home" "Home")
 (link-to "#category" "Category")
 "Current page")

(pager
 (link-to "#previous" "Previous")
 (link-to "#next" "Next"))
```


## Modals

```clojure
(modal
 (modal-header "Question for You")
 [:div.modal-body
  [:p "What does Marcellus Wallace look like?"]]
 [:div.modal-footer
  (modal-dismiss-link {:class "btn"} "Wha-wha…?")])
```


## Display elements

```clojure
(accordion "myAccordion"
           ["Introduction"
            [:p "Here's some things you need to know."]]
           ["More info"
            [:p "You might also find this interesting."]])

(carousel "myCarousel"
          [(image "tibet.png") [:div.caption [:h4 "Top of the world"]]]
          [(image "lhasa.png") [:div.caption [:h4 "Off to Potala palace"]]])

(thumbnails "span4"
            ["tibet.png"]
            ["potala.png" "Potala Palace"]
            ["kailash.png" "Mount Kailash" "#kailash"])
```


## Tables

```clojure
(table "Contact Info"
       ["Name" "Email"]
       ["Joe" "joe@example.com"]
       ["Jenny" "jenny@example.com"])

(thead "Name" "Email")

(tbody ["Joe" "joe@example.com"]
       ["Jenny" "jenny@example.com"])
```


## Design philosophy

* Use vanilla Hiccup unless a custom function better communicates the
  intent and is more succinct
* Build components to meet real needs
* For consistency, create components using `hiccup.def/defelem`


## Contributing

If you have suggestions for the library, you are welcome to open up a
[new issue](https://github.com/asmala/giddyup/issues/new). I also
welcome code contributions, in which case I would recommend a
[pull request](https://help.github.com/articles/using-pull-requests)
with a feature branch.


## License

Copyright © 2012 Janne Asmala

Distributed under the
[Eclipse Public License](http://www.eclipse.org/legal/epl-v10.html),
the same as Clojure.