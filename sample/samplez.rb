require 'num4probstdy'
require_relative('mymatcher')

RSpec.describe Num4ProbStdyLib do
    context '#default' do
        it '#dspbdist' do
            expect(
                Num4ProbStdyLib.dspbdist()
            ).to is_exist("bernoulli.jpg")
        end
        it '#dspbdist2' do
            expect(
                Num4ProbStdyLib.dspbdist2()
            ).to is_exist("weekLawOfLargeNums.jpg")
        end
        it '#dspbdist3' do
            expect(
                Num4ProbStdyLib.dspbdist3(0.55)
            ).to is_exist("condorcet.jpg")
        end
        it '#dspbdist4' do
            expect(
                Num4ProbStdyLib.dspbdist4()
            ).to is_exist("rndwalk.jpg")
        end
        it '#dspbdist5' do
            expect(
                Num4ProbStdyLib.dspbdist5()
            ).to is_exist("martine.jpg")
        end
        it '#dspbdist6' do
            expect(
                Num4ProbStdyLib.dspbdist6()
            ).to is_exist("brown.jpg")
        end
        it '#dspbdist7' do
            expect(
                Num4ProbStdyLib.dspbdist7()
            ).to is_exist("blackScholes.jpg")
        end

    end
    context '#normal' do
        it '#dspbdist' do
            expect(
                Num4ProbStdyLib.dspbdist(1000)
            ).to is_exist("bernoulli.jpg")
        end
        it '#dspbdist2' do
            expect(
                Num4ProbStdyLib.dspbdist2(1000)
            ).to is_exist("weekLawOfLargeNums.jpg")
        end
        it '#dspbdist3' do
            expect(
                Num4ProbStdyLib.dspbdist3(0.55, 1000)
            ).to is_exist("condorcet.jpg")
        end
        it '#dspbdist4' do
            expect(
                Num4ProbStdyLib.dspbdist4(1000)
            ).to is_exist("rndwalk.jpg")
        end
        it '#dspbdist5' do
            expect(
                Num4ProbStdyLib.dspbdist5(1000)
            ).to is_exist("martine.jpg")
        end
        it '#dspbdist6' do
            expect(
                Num4ProbStdyLib.dspbdist6(1000)
            ).to is_exist("brown.jpg")
        end
        it '#dspbdist7' do
            expect(
                Num4ProbStdyLib.dspbdist7(1000)
            ).to is_exist("blackScholes.jpg")
        end
    end
end


