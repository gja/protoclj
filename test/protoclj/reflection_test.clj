(ns protoclj.reflection-test
  (:require [protoclj.reflection :refer :all]
            [clojure.test :refer :all])
  (:import [protoclj
            Sample1
            Sample1$KeyValuePair
            Sample1$EmbeddedObject]))

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

(deftest proto-attributes-test
  (testing "it lists all the proto attributes for a simple attribute"
    (is (= #{{:name :key :reader "getKey" :writer "setKey" :attribute-type :regular :type "java.lang.String"}
             {:name :value :reader "getValue" :writer "setValue" :attribute-type :regular :type "java.lang.String"}}
           (->> (proto-attributes Sample1$KeyValuePair)
                (map (fn [m]
                       {:name (:name-kw m)
                        :reader (.getName (:reader m))
                        :writer (.getName (:writer m))
                        :type (.getName (:type m))
                        :attribute-type (:attribute-type m)}))
                (into #{})))))

  (testing "it lists all the proto attributes for an embedded object"
    (is (= #{{:name :obj, :reader "getObj", :writer "setObj", :type "protoclj.Sample1$EmbeddedObject$AnonymousObject", :attribute-type :regular}}
           (->> (proto-attributes Sample1$EmbeddedObject)
                (map (fn [m]
                       {:name (:name-kw m)
                        :reader (.getName (:reader m))
                        :writer (.getName (:writer m))
                        :type (.getName (:type m))
                        :attribute-type (:attribute-type m)}))
                (into #{}))))))
