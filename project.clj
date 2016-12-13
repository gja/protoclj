(defproject protoclj "0.2.1"
  :description "A protobuffer compiler for clojure wrappers"
  :url "https://github.com/nilenso/protoclj"
  :license {:name "The MIT License"}
  :dependencies [[org.clojure/clojure "1.8.0"]
		 [com.google.protobuf/protobuf-java "3.1.0"]]
  :java-source-paths ["test/fixtures"]
  :test-paths ["test/fixtures", "test"])
