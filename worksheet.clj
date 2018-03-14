;; gorilla-repl.fileformat = 1

;; **
;;; # Propel Worksheet
;;; 
;;; 
;; **

;; @@
(ns worksheet
  (:require [gorilla-plot.core :as plot]))

(use 'propel.core)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@


(interpret-program '(3 5 integer_* "hello" 4 "world" integer_-)
                   empty-push-state)

(interpret-program '(1 (2 integer_+))
                   empty-push-state)
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:exec</span>","value":":exec"},{"type":"list-like","open":"<span class='clj-lazy-seq'>(</span>","close":"<span class='clj-lazy-seq'>)</span>","separator":" ","items":[],"value":"()"}],"value":"[:exec ()]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:integer</span>","value":":integer"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>3</span>","value":"3"}],"value":"(3)"}],"value":"[:integer (3)]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:string</span>","value":":string"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[],"value":"()"}],"value":"[:string ()]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:boolean</span>","value":":boolean"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[],"value":"()"}],"value":"[:boolean ()]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:input</span>","value":":input"},{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[],"value":"{}"}],"value":"[:input {}]"}],"value":"{:exec (), :integer (3), :string (), :boolean (), :input {}}"}
;; <=

;; @@

;; @@

;; @@

;; @@

;; @@
(def opens {'exec_dup 1 'exec_if 2}) ; blocks opened (default = 0)

(defn push-from-plushy
  "Returns the Push program expressed by the given plushy representation."
  [plushy]
  (let [opener? #(and (vector? %) (= (first %) 'open))] ;; [open <n>] marks opens
    (loop [push () ;; iteratively build the Push program from the plushy
           plushy (mapcat #(if-let [n (get opens %)] [% ['open n]] [%]) plushy)]
      (if (empty? plushy)       ;; maybe we're done?
        (if (some opener? push) ;; done with plushy, but unclosed open
          (recur push '(close)) ;; recur with one more close
          push)                 ;; otherwise, really done, return push
        (let [i (first plushy)]
          (if (= i 'close) 
            (if (some opener? push) ;; process a close when there's an open
              (recur (let [post-open (reverse (take-while (comp not opener?)
                                                          (reverse push)))
                           open-index (- (count push) (count post-open) 1)
                           num-open (second (nth push open-index))
                           pre-open (take open-index push)]
                       (if (= 1 num-open)
                         (concat pre-open [post-open])
                         (concat pre-open [post-open ['open (dec num-open)]])))
                     (rest plushy))
              (recur push (rest plushy))) ;; unmatched close, ignore
            (recur (concat push [i]) (rest plushy)))))))) ;; anything else
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;user/opens</span>","value":"#'user/opens"}
;; <=

;; @@
(push-from-plushy '())
;=> ()

(push-from-plushy '(1 2 3))
;=> (1 2 3)

(push-from-plushy '(1 2 exec_dup 3))
;=> (1 2 exec_dup (3))

(push-from-plushy '(1 2 exec_if 3))
;=> (1 2 exec_if (3) ())

(push-from-plushy '(exec_dup exec_if exec_dup))
;=> (exec_dup (exec_if (exec_dup ()) ()))

(push-from-plushy '(1 2 exec_if 3))
;=> (1 2 exec_if (3) ())

(push-from-plushy '(1 close exec_if 2 3 close 4 exec_dup 5 close 6 close 7))
;=> (1 exec_if (2 3) (4 exec_dup (5) 6) 7)
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-lazy-seq'>(</span>","close":"<span class='clj-lazy-seq'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-symbol'>exec_if</span>","value":"exec_if"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>2</span>","value":"2"},{"type":"html","content":"<span class='clj-long'>3</span>","value":"3"}],"value":"(2 3)"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>4</span>","value":"4"},{"type":"html","content":"<span class='clj-symbol'>exec_dup</span>","value":"exec_dup"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>5</span>","value":"5"}],"value":"(5)"},{"type":"html","content":"<span class='clj-long'>6</span>","value":"6"}],"value":"(4 exec_dup (5) 6)"},{"type":"html","content":"<span class='clj-long'>7</span>","value":"7"}],"value":"(1 exec_if (2 3) (4 exec_dup (5) 6) 7)"}
;; <=

;; @@

;; @@
