require 'java'
require 'num4probstdy.jar'
require 'commons-math3-3.6.1.jar'
require 'jfreechart-1.5.4.jar'

java_import 'ProbStdy'
module Num4ProbStdyLib
    # 確率・統計について、勉強用です
    class << self
        # 大数の強法則
        #
        # @overload dspbdist(n=1200)
        #   @param [int] n 回数
        #   @return [void] bernoulli.jpgファイル出力
        def dspbdist(n=1200)
            ProbStdy.dspbDist(n)
        end
        # 大数の弱法則
        #
        # @overload dspbdist2(n=1200)
        #   @param [int] n 回数
        #   @return [void] weekLawOfLargeNums.jpgファイル出力
        def dspbdist2(n=1200)
            ProbStdy.dspbDist2(n)
        end
        # 陪審定理
        #
        # @overload dspbdist3(p, n=1200)
        #   @param [double] p 確率
        #   @param [int]    n 回数
        #   @return [void] condorcet.jpgファイル出力
        def dspbdist3(p, n=1200)
            ProbStdy.dspbDist3(p, n)
        end
        # 単純ランダム・ウォーク
        #
        # @overload dspbdist4(n=1200)
        #   @param [int] n 回数
        #   @return [void] rndwalk.jpgファイル出力
        def dspbdist4(n=1200)
            ProbStdy.dspbDist4(n)
        end
        # マルチンゲール性の検証
        #
        # @overload dspbdist5(n=600)
        #   @param [int] n 回数
        #   @return [void] martine.jpgファイル出力
        def dspbdist5(n=600)
            ProbStdy.dspbDist5(n)
        end
        # ブラウン運動
        #
        # @overload dspbdist4(n=1200)
        #   @param [int] n 回数
        #   @return [void] brown.jpgファイル出力
        def dspbdist6(n=1200)
            ProbStdy.dspbDist6(n)
        end
        # ブラック＝ショールズ過程
        #
        # @overload dspbdist5(n=600)
        #   @param [int] n 回数
        #   @return [void] blackScholes.jpgファイル出力
        def dspbdist7(n=600)
            ProbStdy.dspbDist7(n)
        end
    end
end

