Giddyup: Bootstrap for Hiccup
=============================

A library of [Bootstrap](http://getbootstrap.com/) helpers for
[Hiccup](https://github.com/weavejester/hiccup/).


## Forms

Easily create Bootstrap styled forms:

```clojure
(fielset
 "New account"
 (wrapper
  (label :email "Email")
  (email-field {:placeholder "joe@example.com"} :email)
  nil
  (hint "Double-check your email address"))
 (wrapper
  (label :password "Passoword")
  (password-field :password)))

(actions
 (button-link-to "/" "Cancel")
 (submit-button "Sign up"))
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

Distributed under the Eclipse Public License, the same as Clojure.
