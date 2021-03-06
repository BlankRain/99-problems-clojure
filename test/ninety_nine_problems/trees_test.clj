(ns ninety-nine-problems.trees-test
    (:require
        [clojure.test :refer :all]
        [ninety-nine-problems.trees :refer :all]))

(deftest tree?-test-true
    (is (tree? [:a [:b nil nil] nil])))

(deftest tree?-test-false
    (is (not (tree? [:a [:b nil nil]]))))

(deftest same-tree?-test-nil-true
    (is (same-tree? nil nil)))

(deftest same-tree?-test-first-nil-false
    (is (not (same-tree? nil [:x nil nil]))))

(deftest same-tree?-test-second-nil-false
    (is (not (same-tree? [:x nil nil] nil))))

(deftest same-tree?-test-leaves-true
    (is (same-tree? [:x nil nil] [:y nil nil])))

(deftest same-tree-test-true-two
    (is (same-tree? [:x [:y nil nil] nil] [:a [:b nil nil] nil])))

(deftest same-tree-test-false-two
    (is (not (same-tree? [:x [:y nil nil] nil] [:x nil [:y nil nil]]))))

(deftest symmetric?-nil
    (is (symmetric? nil)))

(deftest symmetric?-one
    (is (symmetric? [:x nil nil])))

(deftest symmetric?-two
    (is (not (symmetric? [:x [:y nil nil] nil]))))

(deftest symmetric?-three
    (is (symmetric? [:x [:y nil nil] [:z nil nil]])))

(deftest symmetric?-four
    (is (not (symmetric? [:x [:y nil nil] [:z nil [:a nil nil]]]))))

(deftest symmetric?-true-five
    (is (symmetric? [:x [:y nil [:z nil nil]] [:a nil [:b nil nil]]])))

(deftest symmetric?-false-five
    (is (not (symmetric? [:x [:y nil [:z nil nil]] [:a [:b nil nil] nil]]))))

(deftest bst-add-test-empty
    (is (= [1 nil nil] (bst-add nil 1))))

(deftest bst-add-test-left
    (is (= [1 [0 nil nil] nil] (bst-add [1 nil nil] 0))))

(deftest bst-add-test-right
    (is (= [1 nil [2 nil nil]] (bst-add [1 nil nil] 2))))

(deftest bst-add-test-right-left
    (is (= [1 nil [3 [2 nil nil] nil]] (bst-add [1 nil [3 nil nil]] 2))))

(deftest bst-test
    (is (= [3 [2 [1 nil nil] nil] [5 nil [7 nil nil]]] (bst '(3 2 5 7 1)))))

(deftest leaf?-test-empty
    (is (not (leaf? nil))))

(deftest leaf?-test-leaf
    (is (leaf? [1 nil nil])))

(deftest leaf?-test-left-child
    (is (not (leaf? [1 [2 nil nil] nil]))))

(deftest leaf?-test-right-child
    (is (not (leaf? [1 nil [2 nil nil]]))))

(deftest leaf?-test-two-children
    (is (not (leaf? [1 [2 nil nil] [3 nil nil]]))))

(deftest count-leaves-test-empty
    (is (zero? (count-leaves nil))))

(deftest count-leaves-test-leaf
    (is (= 1 (count-leaves [1 nil nil]))))

(deftest count-leaves-test-one-left
    (is (= 1 (count-leaves [1 [2 nil nil] nil]))))

(deftest count-leaves-test-one-right
    (is (= 1 (count-leaves [1 nil [2 nil nil]]))))

(deftest count-leaves-test-three-two
    (is (= 2 (count-leaves [1 [2 nil nil] [3 nil nil]]))))

(deftest count-leaves-test-one-left-right
    (is (= 1 (count-leaves [1 [2 nil [3 nil nil]] nil]))))

(deftest leaves-test-empty
    (is (= '() (leaves nil))))

(deftest leaves-test-leaf
    (is (= '(1) (leaves [1 nil nil]))))

(deftest leaves-test-one-left
    (is (= '(2) (leaves [1 [2 nil nil] nil]))))

(deftest leaves-test-one-right
    (is (= '(2) (leaves [1 nil [2 nil nil]]))))

(deftest leaves-test-three-two
    (is (= '(2 3) (leaves [1 [2 nil nil] [3 nil nil]]))))

(deftest leaves-test-one-left-right
    (is (= '(3) (leaves [1 [2 nil [3 nil nil]] nil]))))

(deftest internals-test-empty
    (is (= '() (internals nil))))

(deftest internals-test-leaf
    (is (= '() (internals [1 nil nil]))))

(deftest internals-test-one-left
    (is (= '(1) (internals [1 [2 nil nil] nil]))))

(deftest internals-test-one-right
    (is (= '(1) (internals [1 nil [2 nil nil]]))))

(deftest internals-test-three-two
    (is (= '(1) (internals [1 [2 nil nil] [3 nil nil]]))))

(deftest internals-test-one-left-right
    (is (= '(1 2) (internals [1 [2 nil [3 nil nil]] nil]))))

(deftest at-level-test-empty
    (is (= '() (at-level 1 nil))))

(deftest at-level-test-leaf-one
    (is (= '(1) (at-level 1 [1 nil nil]))))

(deftest at-level-test-leaf-two
    (is (= '() (at-level 2 [1 nil nil]))))

(deftest at-level-test-left-two
    (is (= '(2) (at-level 2 [1 [2 nil nil] nil]))))

(deftest at-level-test-right-two
    (is (= '(2) (at-level 2 [1 nil [2 nil nil]]))))

(deftest at-level-test-three-one
    (is (= '(1) (at-level 1 [1 [2 nil nil] [3 nil nil]]))))

(deftest at-level-test-three-two
    (is (= '(2 3) (at-level 2 [1 [2 nil nil] [3 nil nil]]))))

(deftest at-leveltest-right-left-two
    (is (= '(2) (at-level 2 [1 nil [2 [3 nil nil] nil] nil]))))

(deftest at-leveltest-right-left-three
    (is (= '(3) (at-level 3 [1 nil [2 [3 nil nil] nil] nil]))))

(deftest preorder-test
    (is (= '(1 2 3 4) (preorder [1 [2 nil nil] [3 [4 nil nil] nil]]))))

(deftest inorder-test
    (is (= '(2 1 4 3) (inorder [1 [2 nil nil] [3 [4 nil nil] nil]]))))

(deftest tree-from-traversals-test-nil
    (is (nil? (tree-from-traversals '() '()))))

(deftest tree-from-traversals-test
    (is (= [1 [2 nil nil] [3 [4 nil nil] nil]] (tree-from-traversals '(1 2 3 4) '(2 1 4 3)))))

(deftest tree-from-traversals-test-two
    (is (= [1 [2 nil [3 nil nil]] [4 [5 nil nil] nil]] (tree-from-traversals '(1 2 3 4 5) '(2 3 1 5 4)))))
