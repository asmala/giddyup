Giddyup: Bootstrap for Hiccup [![Build Status](https://secure.travis-ci.org/asmala/giddyup.png?branch=master)](http://travis-ci.org/asmala/giddyup)
=============================

A library of [Bootstrap](http://getbootstrap.com/) helpers for
[Hiccup](https://github.com/weavejester/hiccup/).


## Installation

Add the following to your `project.clj`:

```clojure
[giddyup "0.7.0"]
```

For other options, please refer to the library
[Clojars page](https://clojars.org/giddyup).


## Documentation and Examples

You can find examples on the
[component overview page](http://asmala.github.io/giddyup/components.html)
and for more extensive documentation you can refer to the
[API docs](http://asmala.github.com/giddyup).


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

Copyright © 2014 Janne Asmala

Distributed under the
[Eclipse Public License](http://www.eclipse.org/legal/epl-v10.html),
the same as Clojure.
