import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;

import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XIntervalSeries;
import org.jfree.data.xy.XIntervalSeriesCollection;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.ui.RectangleEdge;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;
import java.text.DecimalFormat;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.BinomialDistribution;
public class ProbStdy {
    private static final int NTIME = 5;
    public static void dspbDist(int n) {
        String subTitle = "(ベルヌーイ試行(p=0.1,0.4,0.9)の結果)";
        ChartPlot plot = new DspbDistChartPlot(n);
        JFreeChart chart = plot.createChart("大数の強法則", n);

        chart.addSubtitle(new TextTitle(subTitle));
        plot.writeJPEG("bernoulli.jpg", chart, 1000, 600);        
    }
    public static void dspbDist2(int n) {
        String subTitle = "(平均:0.4 信頼区間:95%)";
        ChartPlot plot = new DspbDist2ChartPlot(n);
        JFreeChart chart = plot.createChart("大数の弱法則", n);

        chart.addSubtitle(new TextTitle(subTitle));
        plot.writeJPEG("weekLawOfLargeNums.jpg", chart, 1200, 900);        
    }
    public static void dspbDist3(double p, int n) {
        String subTitle = 
            String.format("(p=%.2fの場合の多数決が正しい確率P(X > 0.5)", p);
        ChartPlot plot = new DspbDist3ChartPlot(p, n);
        JFreeChart chart = plot.createChart("陪審定理", n);

        chart.addSubtitle(new TextTitle(subTitle));
        plot.writeJPEG("condorcet.jpg", chart, 1000, 600);        
    }
    public static void dspbDist4(int n) {
        String subTitle = 
            String.format("(ベルヌーイ試行(p=0.5,n=%d,time=%d回)の結果)", n, NTIME);
        ChartPlot plot = new DspbDist4ChartPlot(n);
        JFreeChart chart = plot.createChart("単純ランダム・ウォーク", n);

        chart.addSubtitle(new TextTitle(subTitle));
        plot.writeJPEG("rndwalk.jpg", chart, 1000, 600);        
    }
    public static void dspbDist5(int n) {
        String subTitle = 
            String.format("(ベルヌーイ試行(p=0.45,0.5,0.55,n=%d)の結果)", n);
        ChartPlot plot = new DspbDist5ChartPlot(n);
        JFreeChart chart = plot.createChart("マルチンゲール性の検証", n);

        chart.addSubtitle(new TextTitle(subTitle));
        plot.writeJPEG("martine.jpg", chart, 1000, 600);        
    }
    public static void dspbDist6(int n) {
        String subTitle = 
            String.format("(ベルヌーイ試行(p=0.5,n=%d,time=%d回)の結果)", n, NTIME);
        ChartPlot plot = new DspbDist6ChartPlot(n);
        JFreeChart chart = plot.createChart("ブラウン運動", n);

        chart.addSubtitle(new TextTitle(subTitle));
        plot.writeJPEG("brown.jpg", chart, 1000, 600);        
    }
    public static void dspbDist7(int n) {
        String subTitle = 
            String.format(
                "(ベルヌーイ試行(p=0.5,b=%f, n=%d, time=%d回)の結果)", 2.0, n, NTIME);
        ChartPlot plot = new DspbDist7ChartPlot(n);
        JFreeChart chart = plot.createChart("ブラック＝ショールズ過程", n);

        chart.addSubtitle(new TextTitle(subTitle));
        LegendTitle legend = chart.getLegend();
        legend.setPosition(RectangleEdge.RIGHT);
        plot.writeJPEG("blackScholes.jpg", chart, 1000, 600);        
    }

    /*********************************/
    /* interface define              */
    /*********************************/
    private interface ChartPlot {
        XYPlot createPlot(int n);
        default JFreeChart createChart(String title, int n) {
            XYPlot plot = createPlot(n);
            ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());
            JFreeChart chart = new JFreeChart(title, plot);
            
            ChartUtils.applyCurrentTheme(chart);
            return chart;
        }
        default void writeJPEG(String fname, JFreeChart chart, int width, int height) {
            File file = new File(fname);
            try {
                ChartUtils.saveChartAsJPEG(file, chart, width, height);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private interface CreatePlot {
        XYPlot createPlot(int n);
    }
    /*********************************/
    /* Class define                  */
    /*********************************/
    private static class BernoulliTrial {
        double[] p;
        double[] p01;
        double[] p04;
        double[] p09;
        public BernoulliTrial(int n) {
            p   = new double[n];
            p01 = new double[n];
            p04 = new double[n];
            p09 = new double[n];
        }
    }
    private static class WeekLawOfLargeNum {
	double rank;
	int    cnt001;
	int    cnt003;
	int    cnt005;
    }
    private static class RndWal {
        int[] z05;
    }
    private static class MartinGale {
        int[] z045;
        int[] z050;
        int[] z055;
        public MartinGale(int n) {
            z045 = new int[n];
            z050 = new int[n];
            z055 = new int[n];
        }
    }
    private static class Brown {
        int[] z05;
        public Brown(int n) {
            z05 = new int[n];
        }
    }
    private static class BlackSholes {
        int[] z05;
        public BlackSholes(int n) {
            z05 = new int[n];
        }
    }
    // 大数の強法則
    private static class DspbDistChartPlot implements ChartPlot {
        private BernoulliTrial ber = null;
        private Random rand = new Random();
        public DspbDistChartPlot(int n) {
            ber = new BernoulliTrial(n);
            calc(n);
        }
        private void calc(int n) {
            int cnt01 = 0;
            int cnt04 = 0;
            int cnt09 = 0;

            for(int i = 0; i < n; i++) {
                int num = rand.nextInt(100);

                if (10 > num) { cnt01++; }
                if (40 > num) { cnt04++; }
                if (90 > num) { cnt09++; }
                ber.p01[i] = (double)cnt01 / (i + 1.0);
                ber.p04[i] = (double)cnt04 / (i + 1.0);
                ber.p09[i] = (double)cnt09 / (i + 1.0);
            }
        }
        public XYPlot createPlot(int n) {
            CreatePlot plot = new DspbDistPlot();

            return plot.createPlot(n);
        }
        private class DspbDistPlot implements CreatePlot {
            public XYPlot createPlot(int n) {
                XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
                XYToolTipGenerator toolTipGenerator = new StandardXYToolTipGenerator();
                renderer.setDefaultToolTipGenerator(toolTipGenerator);
                // XYPlot
                XYPlot plot = new XYPlot();

                /*--- 横軸 ---*/
                NumberAxis domainAxis = new NumberAxis("回数");
                plot.setDomainAxis(domainAxis);
                domainAxis.setLowerMargin(0.03);
                domainAxis.setUpperMargin(0.03);

                /*--- 縦軸 ---*/
                NumberAxis valueAxis0 = new NumberAxis("確率");
                plot.setRangeAxis(0, valueAxis0);
                plot.setRenderer(0, renderer);
                plot.setDataset(0, createDataset(n));
                valueAxis0.setLowerBound(0.0);
                valueAxis0.setUpperBound(1.0);
                valueAxis0.setTickUnit(new NumberTickUnit(0.1));

                return plot;
            }
            private XYSeriesCollection createDataset(int n) {
                XYSeries p01 = new XYSeries("0.1");
                XYSeries p04 = new XYSeries("0.4");
                XYSeries p09 = new XYSeries("0.9");

                for(int i = 0; i < n; i++) {
                    p01.add(i, ber.p01[i]);
                    p04.add(i, ber.p04[i]);
                    p09.add(i, ber.p09[i]);
                }
                XYSeriesCollection series = new XYSeriesCollection();
                series.addSeries(p01);
                series.addSeries(p04);
                series.addSeries(p09);
                return series;
            }
        }
    }
    // 大数の弱法則
    private static class DspbDist2ChartPlot implements ChartPlot {
        private final int    TIME = 1000;
        private enum Mean {
            Mean01(0.1),
            Mean04(0.4),
            Mean09(0.9);
            // フィールドの定義
            private double id;
            // コンストラクト
            private Mean(double id) { this.id = id; }
            public double getId() { return this.id; }
        };
        private enum SD {
            SD05(0.05),
            SD03(0.03),
            SD01(0.01);
            private double id;
            private SD(double id) { this.id = id; }
            public double getId() { return this.id; }
            public void cntUp(WeekLawOfLargeNum[] weekOfNums, double x,double mu) {
                if (test(x, mu)) {
                    for(WeekLawOfLargeNum weekOfNum : weekOfNums) {
                        if ((weekOfNum.rank - 0.005 <= x) && 
                            (x < (weekOfNum.rank + 0.005))) {
                            switch(this) {
                            case SD05 -> weekOfNum.cnt005++;
                            case SD03 -> weekOfNum.cnt003++;
                            case SD01 -> weekOfNum.cnt001++;
                            default -> 
                                throw new AssertionError("caseが不足しています!"); 
                            }
                        }
                    }
                }
            }
            // チェビシェフの不等式
	    // P(|Xn - μ| ≦ kσ) ≧ 1 - 1/k^2
	    // P(|Xn - μ| > kσ) ≦ 1/k^2
	    //
	    // μ=0.4
            private boolean test(double x, double mu) {
                boolean ok = true;
	        // 0.25 = 1/k^2
	        // 25/100 = 1/k^2
	        // 100/25 = k^2
	        // 10/5 = k
	        // 2 = k
                final double k = 2.0;
                final double kSD = k * this.id;
                if (Math.abs(x - mu) >  kSD) { ok = false; }
                return ok;             
            }
        }
        private enum CLASS {
            CLASS_MIN(0.25),
            CLASS_MAX(0.52);
            private double id;
            private CLASS(double id) { this.id = id; }
            public double getId() { return this.id; }
        };
        private WeekLawOfLargeNum[] weekOfNums = null;
        private Random rand = new Random();
        public DspbDist2ChartPlot(int n) {
            DspBDist2_init(n);
            calc(n);
        }
        private void DspBDist2_init(int n) {
            weekOfNums = 
                new WeekLawOfLargeNum[
                    Double.valueOf(
                        (CLASS.CLASS_MAX.getId() - CLASS.CLASS_MIN.getId()) 
                       / 0.01 + 1.0).intValue()
                ];
            for(int i = 0; i < weekOfNums.length; i++) {
                weekOfNums[i] = new WeekLawOfLargeNum();
                weekOfNums[i].rank = CLASS.CLASS_MIN.getId() + 0.01 * i;
                weekOfNums[i].cnt005 = 0;
                weekOfNums[i].cnt003 = 0;
                weekOfNums[i].cnt001 = 0;
            }
        }
        private void calc(int n) {
            int cnt01 = 0;
            int cnt04 = 0;
            int cnt09 = 0;
            double p01 = 0.0;
            double p04 = 0.0;
            double p09 = 0.0;

            for(int i = 0; i < n; i++) {
                int num = rand.nextInt(TIME);

                if (TIME * Mean.Mean01.getId() > num) { cnt01++; }
                if (TIME * Mean.Mean04.getId() > num) { cnt04++; }
                if (TIME * Mean.Mean09.getId() > num) { cnt09++; }
                
                p01 = (double)cnt01 / (i + 1.0);
                p04 = (double)cnt04 / (i + 1.0);
                p09 = (double)cnt09 / (i + 1.0);

                SD.SD01.cntUp(weekOfNums, p04, Mean.Mean04.getId());
                SD.SD03.cntUp(weekOfNums, p04, Mean.Mean04.getId());
                SD.SD05.cntUp(weekOfNums, p04, Mean.Mean04.getId());
            }
        }
        public XYPlot createPlot(int n) {
            NumberAxis domainAxis = new NumberAxis("階級");
            CombinedDomainXYPlot plot = new CombinedDomainXYPlot();
            /*--- 横軸 ---*/
            plot.setDomainAxis(domainAxis);
            domainAxis.setLowerBound(CLASS.CLASS_MIN.getId() - 0.005);
            domainAxis.setUpperBound(CLASS.CLASS_MAX.getId() + 0.005);
            domainAxis.setLowerMargin(0.03);
            domainAxis.setUpperMargin(0.03);
            domainAxis.setTickUnit(new NumberTickUnit(0.02));
            domainAxis.setNumberFormatOverride(new DecimalFormat("0.0#"));

            plot.add(createPlot_1(n), 1);
            plot.add(createPlot_2(n), 1);
            plot.add(createPlot_3(n), 1);
            return plot;
        }
        // SD:0.05
        private XYPlot createPlot_1(int n) {
            CreatePlot plot = new DspbDist2Plot_1(SD.SD05.getId());

            return plot.createPlot(n);
        }
        // SD:0.03
        private XYPlot createPlot_2(int n) {
            CreatePlot plot = new DspbDist2Plot_2(SD.SD03.getId());

            return plot.createPlot(n);
        }
        // SD:0.01
        private XYPlot createPlot_3(int n) {
            CreatePlot plot = new DspbDist2Plot_3(SD.SD01.getId());

            return plot.createPlot(n);
        }
        private abstract class  AbstractDspbDist2Plot implements CreatePlot {
            private double sd = 0.0;
            public AbstractDspbDist2Plot(double sd) {
                this.sd = sd;
            }
            abstract IntervalXYDataset createDataset0();           // WeekLawOfLargeNum
            private IntervalXYDataset createDataset1(double sd) {
                NormalDistribution nDist = 
                    new NormalDistribution(Mean.Mean04.getId(), sd);
                String yAxis = 
                    String.format("N(%.1f, %.2f^2)", Mean.Mean04.getId(), sd);
                XYSeries p04 = new XYSeries(yAxis);

                for(double x = 0; x + CLASS.CLASS_MIN.getId() <= CLASS.CLASS_MAX.getId(); x += 0.001) {
                    double y = nDist.density(x + CLASS.CLASS_MIN.getId());

                    p04.add(x + CLASS.CLASS_MIN.getId(), y);
                }
                XYSeriesCollection series = new XYSeriesCollection();
                series.addSeries(p04);
                return series;
            }
            public XYPlot createPlot(int n) {
                XYToolTipGenerator toolTipGenerator = new StandardXYToolTipGenerator();
                XYItemRenderer renderer0 = new XYBarRenderer();
                XYItemRenderer renderer1 = new XYLineAndShapeRenderer(true, false);

                renderer0.setDefaultToolTipGenerator(toolTipGenerator);
                renderer1.setDefaultToolTipGenerator(toolTipGenerator);
                
                // XYPlot
                XYPlot plot = new XYPlot();
                plot.setOrientation(PlotOrientation.VERTICAL);
                plot.mapDatasetToRangeAxis(0,0);
                plot.mapDatasetToRangeAxis(1,1);
                plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

                /*--- 縦軸 ---*/
                NumberAxis valueAxis0 = new NumberAxis("度数");
                plot.setRangeAxis(0, valueAxis0);
                plot.setRenderer(0, renderer0);
                plot.setDataset(0, createDataset0());
                valueAxis0.setLowerBound(0.0);
                valueAxis0.setUpperBound(n * 0.5);
                valueAxis0.setTickUnit(new NumberTickUnit(100));

                NumberAxis valueAxis1 = new NumberAxis("N");
                plot.setRangeAxis(1, valueAxis1);
                plot.setRenderer(1, renderer1);
                plot.setDataset(1, createDataset1(sd));
                valueAxis1.setLowerBound(0.0);

                return plot;
            }
        }
        private class DspbDist2Plot_1 extends  AbstractDspbDist2Plot {
            public DspbDist2Plot_1(double sd) {
                super(sd);
            }
            IntervalXYDataset createDataset0() {
                XIntervalSeries p04 = new XIntervalSeries("SD:0.05");

                for(WeekLawOfLargeNum weekOfNum : weekOfNums) {
                    p04.add(
                        weekOfNum.rank, 
                        weekOfNum.rank - 0.005, weekOfNum.rank + 0.005, 
                        weekOfNum.cnt005);
                }
                XIntervalSeriesCollection series = new XIntervalSeriesCollection();
                series.addSeries(p04);
                return series;
            }
        }
        private class DspbDist2Plot_2 extends  AbstractDspbDist2Plot {
            public DspbDist2Plot_2(double sd) {
                super(sd);
            }
            IntervalXYDataset createDataset0() {
                XIntervalSeries p04 = new XIntervalSeries("SD:0.03");

                for(WeekLawOfLargeNum weekOfNum : weekOfNums) {
                    p04.add(
                        weekOfNum.rank, 
                        weekOfNum.rank - 0.005, weekOfNum.rank + 0.005, 
                        weekOfNum.cnt003);
                }
                XIntervalSeriesCollection series = new XIntervalSeriesCollection();
                series.addSeries(p04);
                return series;
            }
        }
        private class DspbDist2Plot_3 extends  AbstractDspbDist2Plot {
            public DspbDist2Plot_3(double sd) {
                super(sd);
            }
            IntervalXYDataset createDataset0() {
                XIntervalSeries p04 = new XIntervalSeries("SD:0.01");

                for(WeekLawOfLargeNum weekOfNum : weekOfNums) {
                    p04.add(
                        weekOfNum.rank, 
                        weekOfNum.rank - 0.005, weekOfNum.rank + 0.005, 
                        weekOfNum.cnt001);
                }
                XIntervalSeriesCollection series = new XIntervalSeriesCollection();
                series.addSeries(p04);
                return series;
            }
        }
    }
    // 陪審定理
    private static class DspbDist3ChartPlot implements ChartPlot {
        private BernoulliTrial ber = null;
        private double p = 0.0;
        public DspbDist3ChartPlot(double p,int n) {
            this.p = p;
            ber = new BernoulliTrial(n);
            calc(n);
        }
        private void calc(int n) {
            for(int x = 0; x < n; x++) {
                BinomialDistribution bidistObj = new BinomialDistribution(x, this.p);

                ber.p[x] = 
                    switch(x) {
                    case 0 -> 0.0;
                    case 1 -> this.p;
                    default -> 
                        1.0 - bidistObj.cumulativeProbability(x / 2);
                };
            }
        }
        public XYPlot createPlot(int n) {
            CreatePlot plot = new DspbDist3Plot();

            return plot.createPlot(n);
        }
        private class DspbDist3Plot implements CreatePlot {
            public XYPlot createPlot(int n) {
                // render
                XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
                XYToolTipGenerator toolTipGenerator = new StandardXYToolTipGenerator();
                renderer.setDefaultToolTipGenerator(toolTipGenerator);

                // XYPlot
                XYPlot plot = new XYPlot();

        	/*--- 横軸 ---*/
                NumberAxis domainAxis = new NumberAxis("回数");
                plot.setDomainAxis(domainAxis);
                domainAxis.setLowerMargin(0.03);
                domainAxis.setUpperMargin(0.03);
                domainAxis.setTickUnit(new NumberTickUnit(100));
        	/*--- 縦軸 ---*/
                NumberAxis valueAxis0 = new NumberAxis("確率");
                plot.setRangeAxis(0, valueAxis0);
                plot.setRenderer(0, renderer);
                plot.setDataset(0, createDataset(n));
                valueAxis0.setLowerBound(0.0);
                valueAxis0.setUpperBound(1.0);
                valueAxis0.setTickUnit(new NumberTickUnit(0.1));
                return plot;
            }
            private XYSeriesCollection createDataset(int n) {
                String yAxis = String.format("P=%.2f", p);
                XYSeries p = new XYSeries(yAxis);

                for(int i = 0; i < n; i++) {
                    p.add(i, ber.p[i]);
                }
                XYSeriesCollection series = new XYSeriesCollection();
                series.addSeries(p);
                return series;
            }            
        }
    }
    // 単純ランダム・ウォーク
    private static class DspbDist4ChartPlot implements ChartPlot {
        private RndWal[] rndwalks = new RndWal[NTIME];
        private Random rand = new Random();
        public DspbDist4ChartPlot(int n) {
            calc(n);
        }
        private void calc(int n) {
            for(int i = 0; i < rndwalks.length; i++) {
                rndwalks[i] = new RndWal();
                rndwalks[i].z05 = new int[n];

                for(int j = 0; j < n; j++) {
                    int num = rand.nextInt(100);

                    rndwalks[i].z05[j] = (50 > num) ? 1 : -1;
                }
            }
        }
        public XYPlot createPlot(int n) {
            CreatePlot plot = new DspbDist4Plot();

            return plot.createPlot(n);
        }
        private class DspbDist4Plot implements CreatePlot {
            public XYPlot createPlot(int n) {
                // render
                XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
                XYToolTipGenerator toolTipGenerator = new StandardXYToolTipGenerator();
                renderer.setDefaultToolTipGenerator(toolTipGenerator);
                // XYPlot
                XYPlot plot = new XYPlot();
        	/*--- 横軸 ---*/
                NumberAxis domainAxis = new NumberAxis("回数");
                plot.setDomainAxis(domainAxis);
                domainAxis.setLowerMargin(0.03);
                domainAxis.setUpperMargin(0.03);
        	/*--- 縦軸 ---*/
                NumberAxis valueAxis0 = new NumberAxis("カウント");
                plot.setRangeAxis(0, valueAxis0);
                plot.setRenderer(0, renderer);
                plot.setDataset(0, createDataset(n));

                return plot;
            }
            private XYSeriesCollection createDataset(int n) {
                XYSeriesCollection series = new XYSeriesCollection();

                for(int i = 0; i < rndwalks.length; i++) {
	            int cntWalk05 = 0;
                    String p05_x = String.format("0.5_%d", i);
                    XYSeries p05 = new XYSeries(p05_x);

                    for(int j = 0; j < n; j++) {
                        p05.add(j, cntWalk05);
                        cntWalk05 += rndwalks[i].z05[j];
                    }
                    series.addSeries(p05);
                }
                return series;
            }
        }
    }
    // マルチンゲール性の検証
    private static class DspbDist5ChartPlot implements ChartPlot {
        private MartinGale martin = null;
        private Random rand = new Random();
        public DspbDist5ChartPlot(int n) {
             martin = new MartinGale(n);
             calc(n);
        }
        private void calc(int n) {
            for(int i = 0; i < n; i++) {
                int num = rand.nextInt(100);
                
                martin.z045[i] = (45 > num) ? 1 : -1;
                martin.z050[i] = (50 > num) ? 1 : -1;
                martin.z055[i] = (55 > num) ? 1 : -1;
            }
        }
        public XYPlot createPlot(int n) {
            CreatePlot plot = new DspbDist5Plot();

            return plot.createPlot(n);
        }
        private class DspbDist5Plot implements CreatePlot {
            public XYPlot createPlot(int n) {
                NumberAxis domainAxis = new NumberAxis("階級");
                CombinedDomainXYPlot plot = new CombinedDomainXYPlot();
                plot.setDomainAxis(domainAxis);
                domainAxis.setLowerMargin(0.03);
                domainAxis.setUpperMargin(0.03);

                plot.add(createPlot_1(n), 1);
                plot.add(createPlot_2(n), 1);
                plot.add(createPlot_3(n), 1);
                return plot;
            }
            private XYPlot createPlot_1(int n) {
                CreatePlot plot = new  CreatePlot0(0.45, martin.z045);

                return plot.createPlot(n);
            }
            private XYPlot createPlot_2(int n) {
                CreatePlot plot = new  CreatePlot0(0.50, martin.z050);

                return plot.createPlot(n);
            }
            private XYPlot createPlot_3(int n) {
                CreatePlot plot = new  CreatePlot0(0.55, martin.z055);

                return plot.createPlot(n);
            }
            private class CreatePlot0 implements CreatePlot {
                private double px = 0.0;
                private int[] z = null;
                public CreatePlot0(double p, int[] z) {
                    this.px = p;
                    this.z = z;
                }
                public XYPlot createPlot(int n) {
                    // render
                    XYItemRenderer renderer0 = new XYLineAndShapeRenderer(true, false);
                    XYItemRenderer renderer1 = new XYLineAndShapeRenderer(true, false);
                    XYItemRenderer renderer2 = new XYLineAndShapeRenderer(true, false);
                    XYToolTipGenerator generator = new StandardXYToolTipGenerator();
                    renderer0.setDefaultToolTipGenerator(generator);
                    renderer1.setDefaultToolTipGenerator(generator);
                    renderer2.setDefaultToolTipGenerator(generator);
                    // XYPlot
                    XYPlot plot = new XYPlot();

                    plot.setOrientation(PlotOrientation.VERTICAL);
                    plot.mapDatasetToRangeAxis(0,0);
                    plot.mapDatasetToRangeAxis(1,1);
                    plot.mapDatasetToRangeAxis(2,2);
                    plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

                    /*--- 横軸 ---*/
                    /*--- 縦軸 ---*/
                    NumberAxis valueAxis0 = new NumberAxis("カウント");
                    plot.setRangeAxis(0, valueAxis0);
                    plot.setRenderer(0, renderer0);
                    plot.setDataset(0, createDataset0(n));

                    NumberAxis valueAxis1 = new NumberAxis("E");
                    plot.setRangeAxis(1, valueAxis1);
                    plot.setRenderer(1, renderer1);
                    plot.setDataset(1, createDataset1(n));
                    valueAxis1.setLowerBound(-1 * px);
                    valueAxis1.setUpperBound(px);
                    valueAxis1.setTickUnit(new NumberTickUnit(0.1));
                    valueAxis1.setNumberFormatOverride(new DecimalFormat("0.0#"));

                    NumberAxis valueAxis2 = new NumberAxis("CE");
                    plot.setRangeAxis(2, valueAxis2);
                    plot.setRenderer(2, renderer2);
                    plot.setDataset(2, createDataset2(n));
                    valueAxis2.setLowerBound(0.0);
                    valueAxis2.setUpperBound(1.0);
                    valueAxis2.setTickUnit(new NumberTickUnit(0.2));
                    valueAxis2.setNumberFormatOverride(new DecimalFormat("0.0#"));
                    return plot;
                }
                // 確率
                private XYSeriesCollection createDataset0(int n) {
                    String key = String.format("P%.02f", px);
                    int cntMartin = 0;
                    XYSeries p = new XYSeries(key);

                    for(int i = 0; i < n; i++) {
                        p.add(i, cntMartin);
                        cntMartin += z[i];
                    }
                    XYSeriesCollection series = new XYSeriesCollection();
                    series.addSeries(p);
                    return series;
                }
                // 平均
                private XYSeriesCollection createDataset1(int n) {
                    String key = String.format("E%.02f", px);
                    int cnty = 0;
                    XYSeries e = new XYSeries(key);
                    
                    for(int i = 0; i < n; i++) {
                        if (1 == z[i]) { cnty++; }
                        double py = cnty / (i + 1.0);
                        double qy = 1.0 - py;

                        e.add(i, py - qy);
                    }
                    XYSeriesCollection series = new XYSeriesCollection();
                    series.addSeries(e);
                    return series;
                }
                // 平均
                private XYSeriesCollection createDataset2(int n) {
                    double qx = 1.0 - px;
                    int cnty = 0;
                    double by = 0.0;
                    String key = String.format("CE%.02f", px);
                    XYSeries ce = new XYSeries(key);

                    for(int i = 0; i < n; i++) {
                        if (1 == z[i]) { cnty++; }
                        double py = cnty / (i + 1.0);
                        double qy = 1.0 - py;

                        ce.add(i, by + py - qy);
                        by = (1 == z[i]) ? py : qy;
                    }
                    XYSeriesCollection series = new XYSeriesCollection();
                    series.addSeries(ce);
                    return series;
                }
            }
        }
    }
    // ブラウン運動
    private static class DspbDist6ChartPlot implements ChartPlot {
        private Brown[] brown = new Brown[NTIME];
        private Random rand = new Random();
        public DspbDist6ChartPlot(int n) {
            calc(n);
        }
        private void calc(int n) {
            for(int i = 0; i < brown.length; i++) {
                brown[i] = new Brown(n);
                for(int j = 0; j < n; j++) {
                    int num = rand.nextInt(100);

                    brown[i].z05[j] = (50 > num) ? 1 : -1;
                }
            }            
        }
        public XYPlot createPlot(int n) {
            CreatePlot plot = new DspbDist6Plot();

            return plot.createPlot(n);
        }
        private class DspbDist6Plot implements CreatePlot {
           public XYPlot createPlot(int n) {
                // render
                XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
                XYToolTipGenerator toolTipGenerator = new StandardXYToolTipGenerator();
                renderer.setDefaultToolTipGenerator(toolTipGenerator);
                // XYPlot
                XYPlot plot = new XYPlot();
                plot.setOrientation(PlotOrientation.VERTICAL);
                plot.mapDatasetToRangeAxis(0,0);
                plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

        	/*--- 横軸 ---*/
                NumberAxis domainAxis = new NumberAxis("回数");
                plot.setDomainAxis(domainAxis);
                domainAxis.setLowerMargin(0.03);
                domainAxis.setUpperMargin(0.03);
                /*--- 縦軸 ---*/
                NumberAxis valueAxis0 = new NumberAxis("カウント");
                plot.setRangeAxis(0, valueAxis0);
                plot.setRenderer(0, renderer);
                plot.setDataset(0, createDataset(n));

                return plot;
            }
            private XYSeriesCollection createDataset(int n) {
                XYSeriesCollection series = new XYSeriesCollection();
                double dt = 1.0 / n;
                double dr = 1.0 / Math.sqrt(n);

                for(int i = 0; i < brown.length; i++) {
                    double wt = 0;
                    String p05_x = String.format("0.5_%d", i);
                    XYSeries p05 = new XYSeries(p05_x);

                    for(int j = 0; j < n; j++) {
                        double t = j * dt;

                        p05.add(t, wt);
                        wt += brown[i].z05[j] * dr;
                    }
                    series.addSeries(p05);
                }
                return series;
            }
        }
    }
    // ブラック＝ショールズ過程
    private static class DspbDist7ChartPlot implements ChartPlot {
        private BlackSholes[] bs = new BlackSholes[20];
        private Random rand = new Random();
        public DspbDist7ChartPlot(int n) {
            calc(n);
        }
        private void calc(int n) {
            for(int i = 0; i < bs.length; i++) {
                bs[i] = new BlackSholes(n);

                for(int j = 0; j < n; j++) {
                    int num = rand.nextInt(100);

                    bs[i].z05[j] = (50 > num) ? 1 : -1;
                }
            }
        }
        public XYPlot createPlot(int n) {
            CreatePlot plot = new DspbDist7Plot();

            return plot.createPlot(n);
        }
        private class DspbDist7Plot implements CreatePlot {
            public XYPlot createPlot(int n) {
                // render
                XYItemRenderer renderer0 = new XYLineAndShapeRenderer(true, false);
                XYToolTipGenerator generator = new StandardXYToolTipGenerator();
                renderer0.setDefaultToolTipGenerator(generator);

                // XYPlot
                XYPlot plot = new XYPlot();
                plot.setOrientation(PlotOrientation.VERTICAL);
                plot.mapDatasetToRangeAxis(0,0);
                plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

                /*--- 横軸 ---*/
                NumberAxis domainAxis = new NumberAxis("T");
                plot.setDomainAxis(domainAxis);
                domainAxis.setLowerMargin(0.03);
                domainAxis.setUpperMargin(0.03);
                domainAxis.setLowerBound(0.0);
                domainAxis.setUpperBound(1.0);

                /*--- 縦軸 ---*/
                NumberAxis valueAxis0 = new NumberAxis("カウント");
                plot.setRangeAxis(0, valueAxis0);
                plot.setRenderer(0, renderer0);
                plot.setDataset(0, createDataset0(n));
                valueAxis0.setTickUnit(new NumberTickUnit(0.1));
                valueAxis0.setNumberFormatOverride(new DecimalFormat("0.0#"));

                return plot;
            }
            private XYSeriesCollection createDataset0(int n) {
                XYSeriesCollection series = new XYSeriesCollection();

                series.addSeries(createDataset0_0(n, 1.0));
                for(int i = 0; i < bs.length; i++) {
                    String p05_x = String.format("0.5_%d", i+1);

                    series.addSeries(createDataset0_1(p05_x, n, bs[i].z05, 1.0, 2.0));

                }
                return series;
            }
            private  XYSeries createDataset0_0(int n, double a) {
                XYSeries p05 = new XYSeries("normal");
                double dt = 1.0 / n;

                for(int i = 0; i < n; i++) {
                    double t = i * dt;

                    p05.add(t, Math.log(Math.exp(a * t)));
                }
                return p05;
            }
            private  XYSeries createDataset0_1(String key, int n, int[] z05, double a, double b) {
                double dt = 1.0 / n;
                double w0 = 0.0;
                double wt = w0;
                double dw = Math.sqrt(dt);

                XYSeries p05 = new XYSeries(key);
                for(int i = 0; i < n; i++) {
                    double t = i * dt;
                    double bw = 0.5 * b * wt * wt - 0.5 * b * t;
                    double xt = Math.exp(a * t) + bw;

                    p05.add(t, Math.log(xt));
                    wt += z05[i] * dw;
                }
                return p05;
            }
        }
    }
}

