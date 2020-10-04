package com.example.healthcareproject.patientuser;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcareproject.R;

import java.util.ArrayList;
import java.util.List;

public class Nutration extends AppCompatActivity {

    List<Helonuts> heroListnuts;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutration);

        heroListnuts = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView6);
        String[] fruits=new String[]{
                "Apple","Apricot","Orange","Fig","Banana","Avocados","Blood Orange","Blue Berry","Cherries","Pears","Pineapple","Pomegranate","Strawberry","Watermelon","Kiwi","Lemon","Dates","Mangoes","Papaya","Bell Peppers","Squash","Cabbage","Carrot","Celery","Cucumber","Garlic"
        };
        String[] benefits=new String[]{
                "Antioxidant Activity,\nAnti-Hyperglycemia,\nAnti-Diabetes,\nCardiovascular Protection,\nCholesterol Reduction,\nAnti-Asthma,\nWeight Reduction,\nAnti-Cholera,\nBone Protection,\nLung, Breast, Colon and Liver Cancer Prevention.",
                "Good for Healthy Mucusa and Skin,\nProtects from Cancer,\nHelps in Constipation,\nProtects Your Heart,\nProtects Cells from Damage,\nHelps in Formation of Blood,\nGood for Eyes,\nAid Natural Functioning to Body,\nGood for Bones,\nGood Source of Iron,\nRelieve Symptoms of Asthma and Tuberculosis.",
                "Full of Vitamins & Minerals,\nProtects Cardiovascular System,\nKeeps Teeth & Bones Healthy,\nRegulates Blood Pressure,\nLowers Cholesterol,\nProtects Kidneys,\nFights Infections,\nProtects Skins,\nPurifies Blood,\nFull of Fibre.",
                "Power Up Your Weight Loss,\nIncreases Good Cholesterol,\nReduce Risk of Alzheimer's,\nHigh in Anti-Oxidants,\nSupports Muscle Repair,\nPrevents Heart Attack,\nLowers Blood Pressure,\nAnti-Aging,\nCures Migraine,\nPrevents Cancer,\nFights Diabetes,\nAnti-Inflammatory,\nGood for the Skin.",
                "Get Instant Energy,\nKeeps you regular,\nReduce Blood Pressure & the Risk of Heart Disease,\nScavenge Harmful Oxygen-free Radicals,\nReduce Menstrual Cramps,\nContains Anti-Oxidants, Vitamins and Minerals,\nHelps Grow Strong Bones,\nPowers the Brain,\nHelps Prevent Ulcers,\n.Reduces Depression.",
                "May Reduce Risk Factors for Heart Disease,\nContributes to % of daily Fiber,\nAids in Stabilizing Blood Sugar,\nSource of Naturally Good Fats,\nGreat Source of Potassium,\nMaintains a Healthy Heart,\nBoosts Immune System,\nMakes Your Skin Glow,\nAnti-Inflammatory,\nAnti-Aging.",
                "Good Source of Vitamin C,\nGood for Pregnant mother,\nImproving Digeston,\nQuick Wound Healing,\nLowers Obesity,\nControls Diabetes,\nProtects the Retina and Keeps Eyes Healthy,\nStrengthens Bones.",
                "Makes Memory Sharp,\nImproves Digestive System,\nRegulates Blood Sugar Levels,\nContain Useful Anti-Oxidants,\nMaintain Cholesterol Level,\nImproves Fat Regulation,\nUseful for Cancer Patients,\nImproves Vision Sharpness.",
                "Promote Liver Health,\nKeeps Your Heart Healthy,\nHelp Fight Cancer,\nArthritis Relief,\nBone Density,\nGout Relief,\nAnti-Inflammatory,\nReduction In Diabetes Symptoms,\nPromote Kidney Health,\n.Improved Sleep.",
                "Lower Blood Pressure,\nPrevent Cancer,\nEnergy Booster,\nRelieves from Fever,\nBoosts Immune System,\nRich in Fibre,\nLower Cholesterol Level,\nRegulates Bowels,\nAnti-Inflammatory.",
                "Boosts Immune System,\nAnti-Oxidant Protection,\nProtects Skin,\nAsthma Prevention,\nEnergy Production and Anti-Oxidant Defenses,\nPrevents Hypertension,\nStrong Bones Formation,\nHelps in Digestion,\nAnti-Inflammatory Action,\nHava Anti-Cancer Properties.",
                "Rich in Vitamins & Minerals,\nWeight Loss,\nLowers Blood Pressure,\nStomach Disorders,\nCures Diabetes,\nImprove Bone Quality,\nSoothes the Stomach,\nImproves Immunity,\nProtect Cardiovascular Health,\nImproves Memory,\nPrevents Anemia,\nIncrease Appetite,\nPrevents Hair Loss,\nReduce Inflammation,\nReduce Stress Levels,\nLowers Stress Levels,\nGood in Pregnancy,\nAnti-Aging Properties,\n.Prevents Plaque Formation.",
                "Promote Pre-Natal Health,\nBoost Immune System,\nPromote Eye Health,\nFight Bad Cholesterol,\nAnti-Inflammatory,\nAnti-Bacterial,\nAnti-Cancer,\nAnti-Viral,\nAnti-Aging,\nRegulate Blood Pressure.",
                "Highest Alkalising Fruit,\nReduces High Blood Pressure,\nReduce Heart Disease Risk,\nReduce Blood Sugar Level,\nHigh Water Content(%),\nBoosts Immune System,\nStrengthens Bones,\nProduces Energy,\nCleans Kidneys,\nFights Cancer.",
                "Great Source of Anti-Oxidants,\nImproves Heart Health,\nImproves Artery Health,\nLowers Cholesterol,\n X More Vitamin C than an Orange,\nImproves Immune Health,\nGreat Absorption in the Blood,\nImproves Sleep Health,\nLow Glycemic Index Value",
                "Good for Stomach,\nExcellent for Skin Care,\nAids in Digestion,\nAids in Dental Care,\nCures Throat Infections,\nGood for Weight Loss,\nControls High Blood Pressure,\nAssist in Curing Respiratory Disorders,\nGood for Treating Rheumatism,\nReduces Fever,\nActs as a Blood Purifier,\nHair Care Treatment",
                "Heal the Digestive Tract,\nProvide Bone-Strengthening Minerals,\nGreat Blood-Builders,\nNatural Energy Booster,\nAllergy Relief,\nHelp Prevent Cancer,\nMaintain a Healthy Weight,\nNervous System Support,\nReduce the Risk of Stroke,\nRemedy for Sexual Weakness",
                "Cancer Prevention,\nFree Radical Scavenging,\nImmune System Boosting,\nHigh in Copper,\nBoosts Red Blood Cells,\nReduces Cholesterol,\nImproves Memory,\nImproves Vision And Eye Health,\nHelps Reduce Risk Of Heart Disease,\nHelps Maintain Healthy Nerve Function,\nImproves Sex Life",
                "Protects Against Heart Disease,\nImproves Digestion,\nProtects Eye Sight,\nHelps Treat Arthritis,\nImproves Complexion,\nNourishes Hair,\nPrevents Cancer,\nHelps Maintain Healthy Weight,\nHelps Treat Hypertension,\nBoosts Immune System",
                "Loaded with Anti-Oxidants,\n.Anti-Inflammatory Powers,\nRegulates Blood Pressure,\nBurn Calories,\nCures Anaemia,\nGreat Source of Vitamin E,\nHelps Keep Skin Youthful,\nHealthy and Shiny Hair,\nBoosts Immune System,\nAids in Improving Vision,\nLowers Bad Cholesterol,\nCombat Diabetes,\nAnti Cancer",
                "Excellent source of Vitamin C,\nHigh in Fiber to improve cholesterol levels & keep you regular,\nAnti-inflammatory food that helps reduce pain,\nPacked with Carotenoids (great for eye health)),\nHigh in Folate to prevent against birth defects,\n",
                "Contains powerful Anti-Inflammatory properties,\nAids weight-loss and detox,\nProvide cancer prevention,\nFasten the healing of peptic ulcer,\nNatural remedy for heart burn and acid reflex,\nProvides asthma relief,\nPrevent osteoporosis,\nHelps to lower cholesterol,\nHelps in morning sickness and nausea,\nRich in Anti-Oxidants",
                "Improves Skin Health,\nImproves Digestion,\nImproves Kidney Function,\nReduce Incidences of Stroke,\nMaintains a Healthy Heart,\nImproves Liver Function,\nAnti-Bacterial & Viral,\nGreat for Eye Health,\nHealthy Teeth,\nAnti-Cancer",
                "Anti- Cancer,\nCalms Nerves,\nAids Digestion,\nRelieves Arthritis,\nAnti-Inflammatory,\nAssist with Migraines,\nLowers Blood Pressure,\nMaintains Healthy Brain,\nRids Kidney & Gall Stones,\nPrevents Calcium Deposits",
                "Relieves Joint Pain (Arthritis / Gout),\nReduces Cholesterol,\nAids in Weight Loss,\nPromotes Digestion,\nPrevents Headaches,\nFights Cancer,\nRehydrate & Refineries the Body,\nHigh Silica Content,Silky Hair and Skin,\nHelps Diabetes,\nHelps Regulate Blood Pressure",
                "Boosts Immunity,\nReduces Oxidation Stress-Prevents Cancer,\nImproves Iron Metabolism,\nHeart Friendly (Reduces Cholesterol Levels & Blood Pressure Levels),\nReduces Blood Suger Levels,\nCarminative & Helps in Digestion,\nAnti-Viral, Anti-Bacterial & Anti-Microbial,\nRelieves Blood Clotting Disorders,\nAnti-Inflammatory,\nPrevents Respiratory Infections"
        };
        int[] imgages=new int[]{
                R.drawable.appleimage,R.drawable.apricot,R.drawable.orangelast,R.drawable.figs,R.drawable.banana,R.drawable.avocado8,R.drawable.bloodorange8,R.drawable.blueberry8,
                R.drawable.cherry9,R.drawable.pear99,R.drawable.pineapple,R.drawable.pomegranate999,R.drawable.strawberry9,R.drawable.watermelonfinal,R.drawable.kiwi82,
                R.drawable.lemon9,R.drawable.datesfinal,R.drawable.mango99,R.drawable.papayalast,R.drawable.doolmah2,R.drawable.pumpkinkado,
                R.drawable.cabbage,R.drawable.carrot,R.drawable.celery,R.drawable.cucumber,R.drawable.garlic
        };
        int length=fruits.length;
        int index=0;

        while(index<length) {
            heroListnuts.add(new Helonuts(imgages[index],fruits[index],benefits[index]));
            MyListAdapternuts adapternuts = new MyListAdapternuts(this, R.layout.my_custom_listnuts, heroListnuts);
            listView.setAdapter(adapternuts);
            index++;
        }
    }
}