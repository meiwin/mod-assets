# Assets helper module for play 2

This module enables setting up CDNs (content delivery network) for static assets easily.

## Add to your play project

Configure resolver:

```scala
resolvers += "mod-assets" at "http://mod-assets.github.com/repository"
```

Add library dependency:

```scala
libraryDependencies += "mod-assets" %% "mod-assets" % "1.0.0"
```

## Configure `application.conf` with your CDNs

You can configure multiple CDNs that host your static assets.
The CDN will be picked randomly.

If no CDN is configured, the local assets in your project will be used.

```scala
cdns=["http://cdn1.cloudfront.net", "http://cdn2.cloudfront.net"]
```

## Using it in your views

Example:
```scala
<script src="@util.Assets.at("javascripts/jquery-1.7.1.min.js")" type="text/javascript"></script>
```

