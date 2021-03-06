#零碎知识笔记

##属性动画
+ ###ObjectAnimator 动画的执行类
		
		animation.ofFloat(view, "alpha", 1.0F, 0.5F,1.0F)
		透明度从1变到0再变到1
		ObjectAnimator//
                .ofFloat(view, "rotationX", 0.0F, 360.0F)//
                .setDuration(500)//
                .start();
		旋转X轴

+ ###ValueAnimator 实现动画

	ValueAnimator是ObjectAnimator的基类

		valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                image.setTranslationY((float)animation.getAnimatedValue());
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ViewGroup parent = (ViewGroup) image.getParent();
                parent.removeView(image);
            }
        });
		
+ ###TimeInterpolator（时间插值器）：一般用自带的就可以了

	根据时间流逝的百分比计算出当前属性值改变的百分比。

+ ###TypeEvaluator（类型估值算法，即估值器）： 

	根据当前属性改变的百分比来计算改变后的属性值
		
		public class MyTypeEvaluator implements TypeEvaluator<Paint> {
		    @Override
		    public Paint evaluate(float fraction, Paint startValue, Paint endValue) {
		        Log.e("fraction", fraction + "");
		        Paint paint = new Paint();
		        paint.x = 100*fraction * 3f;
		        paint.y = 100 * (fraction * 3f) * (fraction * 3f);
		        return paint;
		    }
		}		

##Butter Knife

   在使用高版本的Butter Knife会出现空指针异常，需要在gradle里面配置
   classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
   并在项目gradle里面添加 apt 'com.jakewharton:butterknife-compiler:8.2.1'
   具体代码如下：
   
   
    Configure your project-level build.gradle to include the 'android-apt' plugin:
    
    buildscript {
      repositories {
        mavenCentral()
       }
      dependencies {
        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
      }
    }
    Then, apply the 'android-apt' plugin in your module-level build.gradle and add the Butter Knife dependencies:
    
    apply plugin: 'android-apt'
    
    android {
      ...
    }
    
    dependencies {
      compile 'com.jakewharton:butterknife:8.2.1'
      apt 'com.jakewharton:butterknife-compiler:8.2.1'
    }
    
##onWindowFocusChanged   
    
     @Override
        public void onWindowFocusChanged(boolean hasFocus) {
            super.onWindowFocusChanged(hasFocus);
        }
        
        
当一个Activity完全加载完毕后，然后就执行

## rxjava retrofit 依赖冲突

```
compile 'com.squareup.retrofit2:retrofit:2.1.0'
compile 'com.squareup.retrofit2:converter-gson:2.1.0'
compile 'io.reactivex.rxjava2:rxjava:2.0.0'
compile 'io.reactivex.rxjava2:rxandroid:2.0.0'
compile 'com.jakewharton.retrofit:retrofit2-rxjava2-adapter:1.0.0'
```

将com.jakewharton.retrofit:retrofit2-rxjava2-adapter替换com.squareup.retrofit2:adapter-rxjava即可