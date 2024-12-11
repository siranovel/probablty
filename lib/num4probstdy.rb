require 'java'
require 'num4probstdy.jar'
require 'commons-math3-3.6.1.jar'
require 'jfreechart-1.5.4.jar'

java_import 'ProbStdy'
module Num4ProbStdyLib
    # 確率・統計について、勉強用です
    class << self
        # 大数の強法則
        def dspbdist(n=1200)
            ProbStdy.dspbDist(n)
        end
        def dspbdist2(n=1200)
            ProbStdy.dspbDist2(n)
        end
        def dspbdist3(p, n=1200)
            ProbStdy.dspbDist3(p, n)
        end
        def dspbdist4(n=1200)
            ProbStdy.dspbDist4(n)
        end
        def dspbdist5(n=600)
            ProbStdy.dspbDist5(n)
        end
        def dspbdist6(n=1200)
            ProbStdy.dspbDist6(n)
        end
        def dspbdist7(n=600)
            ProbStdy.dspbDist7(n)
        end
    end
end

