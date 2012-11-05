# Assets helper module for play 2

This module enables setting up CDNs (content delivery network) for static assets easily.

## Add to your play project

Configure resolver:

```scala
resolvers += "blockthirty releases" at "https://raw.github.com/meiwin/m2repo/master/releases/"
```

Add library dependency:

```scala
libraryDependencies += "blockthirty" %% "mod-assets" % "1.0.1"
```

## Configure `application.conf` with your CDNs

You can configure multiple CDNs that host your static assets.
The CDN will be picked randomly.

If no CDN is configured, the local assets in your project will be used.

```
cdns=["http://cdn1.cloudfront.net", "http://cdn2.cloudfront.net"]
```

## Using it in your views

Example:

```scala
<script src="@util.Assets.at("javascripts/jquery-1.7.1.min.js")" type="text/javascript"></script>
```

If you have multiple assets routes defined, you need to provide `path` parameter

```
...

GET        /assets/*file             controller.Assets.at(path="/public", file)
GET        /assets2/*file            controller.Assets.at(path="/public2", file)

...
```

In your view codes:

```
<script src="@util.Assets.at("/public", "javascripts/jquery-1.7.1.min.js")" type="text/javascript"></script>
```

