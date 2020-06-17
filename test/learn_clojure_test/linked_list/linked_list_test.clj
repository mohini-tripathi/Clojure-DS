(ns learn-clojure-test.linked-list.linked-list-test
  (:require
   [clojure.test :refer [deftest are testing]]
   [learn-clojure.linked-list :as list]))

(deftest test-linkedlist
  (let [node-data-1 1
        node-data-2 4
        list-1 (list/linkedlist)
        list-2 (list/linkedlist 1 2 3 4)]
    (testing "Empty Linked List"
      (are [expected got] (= expected got)
        nil (list/head list-1)
        nil (list/tail list-1)))
    (testing "Non empty linked list"
      (are [expected got] (= expected got)
        node-data-1 (list/head list-2)
        node-data-2 (list/tail list-2)))))

(deftest test-linkedlist-insert
  (let [node-data-1 1
        node-data-2 2
        node-data-3 3
        node-data-4 4
        list-1 (list/linkedlist)
        list-2 (-> list-1
                   (list/prepend node-data-1)
                   (list/prepend node-data-2)
                   (list/prepend node-data-3))
        list-3 (-> list-1 ;; 1->2->3
                   (list/append node-data-1)
                   (list/append node-data-2)
                   (list/append node-data-3))
        list-4 (-> list-1 ;; 2->1->3->4
                   (list/prepend node-data-1)
                   (list/prepend node-data-2)
                   (list/append node-data-3)
                   (list/append node-data-4))]
    (testing "Prepend data to the list"
      (are [expected got] (= expected got)
        node-data-3 (-> list-2
                         (list/head)
                         (list/data))
        node-data-2 (-> list-2
                         (list/head)
                         (list/next)
                         (list/data))
        node-data-1 (-> list-2
                         (list/tail)
                         (list/data))
        node-data-4 (-> list-2
                         (list/prepend node-data-4)
                         (list/head)
                         (list/data))))
    (testing "Append data in the list"
      (are [expected got] (= expected got)
        node-data-1 (-> list-3
                         (list/head)
                         (list/data))
        node-data-3 (-> list-3
                         (list/tail)
                         (list/data))
        node-data-4 (-> list-3
                         (list/append node-data-4)
                         (list/tail)
                         (list/data))
        node-data-2 (-> list-3
                         (list/head)
                         (list/next)
                         (list/data))))
    (testing "Appending and prepending to the list"
      (are [expected got] (= expected got)
        node-data-4 (-> list-4 ; 2->1->3->4
                         (list/tail)
                         (list/data))
        node-data-2 (-> list-4
                         (list/head)
                         (list/data))))))

(deftest test-linkedlist-search
  (let [node-data-1 1
        node-data-2 2
        node-data-3 3
        node-data-4 4
        list-1 (list/linkedlist)
        list-2 (-> list-1
                   (list/prepend node-data-1)
                   (list/prepend node-data-2)
                   (list/prepend node-data-3))
        list-3 (-> list-1 ;; 1->2->3
                   (list/append node-data-1)
                   (list/append node-data-2)
                   (list/append node-data-3))
        list-4 (-> list-1 ;; 2->1->3->4
                   (list/prepend node-data-1)
                   (list/prepend node-data-2)
                   (list/append node-data-3)
                   (list/append node-data-4))]
    (testing "searching data in a list"
      (are [expected got] (= expected got)
        node-data-2 (-> list-4
                         (list/search node-data-2)
                         (list/data))
        nil (-> list-3
                (list/search node-data-4))
        node-data-1 (-> list-2
                         (list/search node-data-1)
                         (list/data))))))

(deftest test-linkedlist-delete
  (let [node-data-1 1
        node-data-2 2
        node-data-3 3
        list-1 (list/linkedlist)
        list-2 (-> list-1
                   (list/prepend node-data-1)
                   (list/prepend node-data-2)
                   (list/prepend node-data-3))]
    (testing "delete head of the list"
      (are [expected got] (= expected got)
        true (-> list-2
                 (list/delete-head)
                 (list/delete-head)
                 (list/delete-head)
                 (list/empty?))
        node-data-1 (-> list-2
                         (list/delete-head)
                         (list/delete-head)
                         (list/head)
                         (list/data))))
    (testing "delete tail of the list"
      (are [expected got] (= expected got)
        true (-> list-2
                 (list/delete-tail)
                 (list/delete-tail)
                 (list/delete-tail)
                 (list/empty?))
        node-data-3 (-> list-2
                         (list/delete-tail)
                         (list/delete-tail)
                         (list/head)
                         (list/data))))))

