package com.vc.presentation.action.captcha;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import com.jhlabs.math.ImageFunction2D;
import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomListColorGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformation;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.GlyphsPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.glyphsvisitor.GlyphsVisitors;
import com.octo.captcha.component.image.textpaster.glyphsvisitor.OverlapGlyphsUsingShapeVisitor;
import com.octo.captcha.component.image.textpaster.glyphsvisitor.TranslateAllToRandomPointVisitor;
import com.octo.captcha.component.image.textpaster.glyphsvisitor.TranslateGlyphsVerticalRandomVisitor;
import com.octo.captcha.component.image.wordtoimage.DeformedComposedWordToImage;
import com.octo.captcha.engine.image.ImageDeformationByBufferedImageOp;
import com.octo.captcha.engine.image.NewListImageCaptchaEngine;
import com.octo.captcha.engine.image.PinchFilter;

/**
 * @author ammen
 * 
 */
public class CaptchaGmailEngine extends NewListImageCaptchaEngine {

	private Integer minAcceptedWordLength, maxAcceptedWordLength, width, weight, fontSize;
	private Font[] fontsList;
	private Color[] fontColorList;

	public CaptchaGmailEngine() {
		this(7, 7, 100, 50, 50, new Font[] { new Font("nyala", Font.BOLD, 50), new Font("Bell MT", Font.PLAIN, 50),
				new Font("Credit valley", Font.BOLD, 50) }, new Color[] { new Color(23, 170, 27), new Color(220, 34, 11),
				new Color(23, 67, 172) });  
	}

	public CaptchaGmailEngine(Integer minAcceptedWordLength, Integer maxAcceptedWordLength, Integer width, Integer weight, Integer fontSize,
			Font[] fontsList, Color[] fontColorList) {
		this.minAcceptedWordLength = minAcceptedWordLength;
		this.maxAcceptedWordLength = maxAcceptedWordLength;
		this.width = width;
		this.weight = weight;
		this.fontSize = fontSize;
		this.fontsList = fontsList;
		this.fontColorList = fontColorList;
		build();
	}

	/**
	 * this method should be implemented as folow :
	 * <ul>
	 * <li>First construct all the factories you want to initialize the gimpy
	 * with</li>
	 * <li>then call the this.addFactoriy method for each factory</li>
	 * </ul>
	 */
	protected void buildInitialFactories() {

	}

	protected void checkFactoriesSize() {

	}

	private void build() {
		// word generator
		com.octo.captcha.component.word.wordgenerator.WordGenerator dictionnaryWords = // new
																						// ConstantWordGenerator("gefefi");
		new com.octo.captcha.component.word.wordgenerator.ComposeDictionaryWordGenerator(
				new com.octo.captcha.component.word.FileDictionary("toddlist"));
		// wordtoimage components
		TextPaster randomPaster = new GlyphsPaster(this.minAcceptedWordLength, this.maxAcceptedWordLength, new RandomListColorGenerator(
				this.fontColorList), new GlyphsVisitors[] { new TranslateGlyphsVerticalRandomVisitor(1),
		// new RotateGlyphsRandomVisitor(Math.PI/32),
				new OverlapGlyphsUsingShapeVisitor(3), new TranslateAllToRandomPointVisitor()
		// ,

				//
				});
		/*
		 * new TextVisitor[]{ new OverlapGlyphsTextVisitor(6) }, null
		 */
		BackgroundGenerator back = new UniColorBackgroundGenerator(this.width, this.weight, Color.white);

		FontGenerator shearedFont = new RandomFontGenerator(this.fontSize, this.fontSize, this.fontsList);

		PinchFilter pinch = new PinchFilter();

		pinch.setAmount(-.5f);
		pinch.setRadius(70);
		pinch.setAngle((float) (Math.PI / 16));
		pinch.setCentreX(0.5f);
		pinch.setCentreY(-0.01f);
		pinch.setEdgeAction(ImageFunction2D.CLAMP);

		PinchFilter pinch2 = new PinchFilter();
		pinch2.setAmount(-.6f);
		pinch2.setRadius(70);
		pinch2.setAngle((float) (Math.PI / 16));
		pinch2.setCentreX(0.3f);
		pinch2.setCentreY(1.01f);
		pinch2.setEdgeAction(ImageFunction2D.CLAMP);

		PinchFilter pinch3 = new PinchFilter();
		pinch3.setAmount(-.6f);
		pinch3.setRadius(70);
		pinch3.setAngle((float) (Math.PI / 16));
		pinch3.setCentreX(0.8f);
		pinch3.setCentreY(-0.01f);
		pinch3.setEdgeAction(ImageFunction2D.CLAMP);

		List<ImageDeformation> textDef = new ArrayList<ImageDeformation>();
		textDef.add(new ImageDeformationByBufferedImageOp(pinch));
		textDef.add(new ImageDeformationByBufferedImageOp(pinch2));
		textDef.add(new ImageDeformationByBufferedImageOp(pinch3));

		// word2image 1
		com.octo.captcha.component.image.wordtoimage.WordToImage word2image;
		word2image = new DeformedComposedWordToImage(shearedFont, back, randomPaster, new ImageDeformationByBufferedImageOp[] {},
				new ImageDeformationByBufferedImageOp[] {}, textDef.toArray(new ImageDeformationByBufferedImageOp[0]));

		this.addFactory(new com.octo.captcha.image.gimpy.GimpyFactory(dictionnaryWords, word2image));
	}

}
