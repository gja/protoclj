(ns protoclj.reflection-test
  (:require [protoclj.reflection :refer :all]
            [clojure.test :refer :all])
  (:import [protoclj Sample1]))

(deftest protobuf-classes-test
  (testing "it lists the classes"
    (is (= #{"protoclj.Sample1$EmbeddedObject"
             "protoclj.Sample1$NestedObject"
             "protoclj.Sample1$OptionalObject"
             "protoclj.Sample1$KeyValuePair"
             "protoclj.Sample1$EmbeddedObject$AnonymousObject"
             "protoclj.Sample1$EnumObject"
             "protoclj.Sample1$RepeatedObject"
             "protoclj.Sample1$EnumObject$TheEnum"}

           (->> (protobuf-classes Sample1 [])
                (map #(-> % :name str))
                (into #{}))))))
