package com.example.tobegood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.RadioGroup;

import com.example.tobegood.dao.UserDao;
import com.example.tobegood.dao.*;
import com.example.tobegood.bean.*;
import com.example.tobegood.bean.User;
import com.example.tobegood.bean.UserPlan;
import com.example.tobegood.dao.UserPlanDao;

import android.widget.Toast;

public class Register extends AppCompatActivity {
    private boolean mySex = true;
    private boolean myVegan = true;
    private boolean myEatDisorder = true;
    private int myPurpose = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setToolbar();
        initialPage();
        Button button_register_register = (Button) findViewById(R.id.Button_register_register);
        RadioGroup radioGroup_register_sex = (RadioGroup) findViewById(R.id.RadioGroup_register_sex);
        RadioGroup radioGroup_register_vegan = (RadioGroup) findViewById(R.id.RadioGroup_register_vegan);
        RadioGroup radioGroup_register_eatdisorder = (RadioGroup) findViewById(R.id.RadioGroup_register_eatdisorder);
        RadioGroup radioGroup_register_purpose = (RadioGroup) findViewById(R.id.RadioGroup_register_purpose);
        radioGroup_register_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkId) {
                switch (checkId) {
                    case R.id.Radiobutton_register_male:
                        mySex = true;
                        break;
                    case R.id.Radiobutton_register_female:
                        mySex = false;
                        break;
                    default:
                        break;
                }
            }
        });
        radioGroup_register_vegan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkId) {
                switch (checkId) {
                    case R.id.Radiobutton_register_vegan:
                        myVegan = true;
                        break;
                    case R.id.Radiobutton_register_notvegan:
                        myVegan = false;
                        break;
                    default:
                        break;
                }
            }
        });
        radioGroup_register_eatdisorder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkId) {
                switch (checkId) {
                    case R.id.Radiobutton_register_eatdisorder:
                        myEatDisorder = true;
                        break;
                    case R.id.Radiobutton_register_noeatdisorder:
                        myEatDisorder = false;
                        break;
                    default:
                        break;
                }
            }
        });
        radioGroup_register_purpose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkId) {
                switch (checkId) {
                    case R.id.Radiobutton_register_purpose1:
                        myPurpose = 1;
                        break;
                    case R.id.Radiobutton_register_purpose2:
                        myPurpose = 2;
                        break;
                    case R.id.Radiobutton_register_purpose3:
                        myPurpose = 3;
                        break;
                    default:
                        break;
                }
            }
        });
        button_register_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDao userDao = new UserDao(Register.this);
                User user = getUser();
                UserPlanDao userPlanDao = new UserPlanDao(Register.this);

                if (findEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill up all the information, thank you.", Toast.LENGTH_SHORT).show();
                } else if (userDao.getUserById(user.getId()) != null) {
                    Toast.makeText(getApplicationContext(), "There is already a same id in the database, please change one.", Toast.LENGTH_SHORT).show();
                } else {
                    userDao.add(user);
                    setUserPlan(user.getId());
                    Toast.makeText(getApplicationContext(), "register successful! Your information is" + user.toString(),
                            Toast.LENGTH_SHORT).show();
                    Log.d("register", "onClick: "+user.toString());
                    initialTable();
                    Intent intent_toMainPage = new Intent(Register.this, MainPage.class);
                    intent_toMainPage.putExtra("usee", user.getId());
                    startActivity(intent_toMainPage);
                }
            }
        });
    }

    private void initialPage() {
        RadioGroup radioGroup_register_sex = (RadioGroup) findViewById(R.id.RadioGroup_register_sex);
        RadioGroup radioGroup_register_vegan = (RadioGroup) findViewById(R.id.RadioGroup_register_vegan);
        RadioGroup radioGroup_register_eatdisorder = (RadioGroup) findViewById(R.id.RadioGroup_register_eatdisorder);
        RadioGroup radioGroup_register_purpose = (RadioGroup) findViewById(R.id.RadioGroup_register_purpose);
        radioGroup_register_sex.check(R.id.Radiobutton_register_male);
        radioGroup_register_vegan.check(R.id.Radiobutton_register_vegan);
        radioGroup_register_eatdisorder.check(R.id.Radiobutton_register_eatdisorder);
        radioGroup_register_purpose.check(R.id.Radiobutton_register_purpose1);
    }

    private User getUser() {
        EditText edit_register_id = (EditText) findViewById(R.id.Edit_register_id);
        EditText edit_register_name = (EditText) findViewById(R.id.Edit_register_name);
        EditText edit_register_password = (EditText) findViewById(R.id.Edit_register_password);
        EditText edit_register_height = (EditText) findViewById(R.id.Edit_register_height);
        EditText edit_register_weight = (EditText) findViewById(R.id.Edit_register_weight);
        EditText edit_register_contactnumber = (EditText) findViewById(R.id.Edit_register_contactnumber);
        User user = new User();
        user.setId(Integer.parseInt(edit_register_id.getText().toString()));
        user.setName(edit_register_name.getText().toString());
        user.setPassword(edit_register_password.getText().toString());
        user.setHeight(Integer.parseInt(edit_register_height.getText().toString()));
        user.setWeight(Integer.parseInt(edit_register_weight.getText().toString()));
        user.setSex(mySex);
        user.setVegan(myVegan);
        user.setEatdisorder(myEatDisorder);
        user.setEmergencyNumber(edit_register_contactnumber.getText().toString());
        user.setLastDay(1);
        user.setPurpose(myPurpose);
        return user;
    }

    private void initialTable() {
        EatTable eatTable1 = new EatTable(1, "Nut Oatmeal Cups", "recipe001",
                "Combine the milk, egg and nut. Add some salt, then bake it for 20 minutes.",
                "Veggie Sandwich", "recipe002",
                "Spread one slice of bread with hummus and the other with avocado. Fill the sandwich with greens, bell pepper, cucumber and carrot. Slice in half and serve.",
                "Chicken Bowl", "recipe003",
                "Step 1\n" +
                        "Place a large rimmed baking sheet in the oven; preheat to 425 degrees F.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Combine chili powder, cumin, 1/2 tsp. salt, garlic powder, paprika, and ground pepper in a large bowl. Transfer 1 tsp. of the spice mixture to a medium bowl and set aside. Whisk 1 Tbsp. oil into the remaining spice mixture in the large bowl. Add chicken, onion, and red and green bell peppers; toss to coat.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Remove the pan from the oven; coat with cooking spray. Spread the chicken mixture in an even layer on the pan. Roast for 15 minutes.\n" +
                        "\n" +
                        "Step 4\n" +
                        "Meanwhile, combine kale and black beans with the remaining 1/4 tsp. salt and 1 Tbsp. olive oil in a large bowl; toss to coat.\n" +
                        "\n" +
                        "Step 5\n" +
                        "Remove the pan from the oven. Stir the chicken and vegetables. Spread kale and beans evenly over the top. Roast until the chicken is cooked through and the vegetables are tender, 5 to 7 minutes more.\n" +
                        "\n" +
                        "Step 6\n" +
                        "Meanwhile, add yogurt, lime juice, and water to the reserved spice mixture; stir to combine.\n" +
                        "\n" +
                        "Step 7\n" +
                        "Divide the chicken and vegetable mixture among 4 bowls. Drizzle with the yogurt dressing and serve.");
        EatTable eatTable2 = new EatTable(2, "Egg", "recipe004",
                "Put two eggs in the boiled water for 10 minutes",
                "Chicken Taco", "recipe005",
                "Step 1\n" +
                        "Preheat oven to 450 degrees F. Line a large rimmed baking sheet with foil.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Combine lime juice, chipotles to taste, honey, garlic and salt in a blender. Process until mostly smooth. Place cauliflower in a large bowl; add the sauce and stir to coat. Transfer to the prepared baking sheet. Sprinkle onion over the cauliflower. Roast, stirring once, until the cauliflower is tender and browned in spots, 18 to 20 minutes; set aside to cool.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Divide quinoa among 4 single-serving lidded containers (1/2 cup each). Top each with one-fourth of the cauliflower mixture, 1/4 cup black beans and 2 tablespoons cheese. Seal the containers and refrigerate for up to 4 days.\n" +
                        "\n" +
                        "Step 4\n" +
                        "To reheat 1 container, vent the lid and microwave on High until steaming, 2 1/2 to 3 minutes. Top with 1/4 cup cabbage and 1/4 avocado (sliced). Serve with a lime wedge, if desired.",
                "Veggie Burger", "recipe006",
                "EatTable2Name3");
        EatTable eatTable3 = new EatTable(3, "Egg", "recipe004",
                "Put two eggs in the boiled water for 10 minutes",
                "Chipotle-Lime Cauliflower Taco Bowls", "recipe008",
                "Step 1\n" +
                        "Preheat oven to 450 degrees F. Line a large rimmed baking sheet with foil.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Combine lime juice, chipotles to taste, honey, garlic and salt in a blender. Process until mostly smooth. Place cauliflower in a large bowl; add the sauce and stir to coat. Transfer to the prepared baking sheet. Sprinkle onion over the cauliflower. Roast, stirring once, until the cauliflower is tender and browned in spots, 18 to 20 minutes; set aside to cool.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Divide quinoa among 4 single-serving lidded containers (1/2 cup each). Top each with one-fourth of the cauliflower mixture, 1/4 cup black beans and 2 tablespoons cheese. Seal the containers and refrigerate for up to 4 days.\n" +
                        "\n" +
                        "Step 4\n" +
                        "To reheat 1 container, vent the lid and microwave on High until steaming, 2 1/2 to 3 minutes. Top with 1/4 cup cabbage and 1/4 avocado (sliced). Serve with a lime wedge, if desired.",
                "Salmon Cakes", "recipe009",
                "Step 1\n" +
                        "Preheat oven to 450 degrees F. Coat a baking sheet with cooking spray.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Heat 1 1/2 teaspoons oil in a large nonstick skillet over medium-high heat. Add onion and celery; cook, stirring, until softened, about 3 minutes. Stir in parsley; remove from the heat.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Place salmon in a medium bowl. Flake apart with a fork; remove any bones and skin. Add egg and mustard; mix well. Add the onion mixture, breadcrumbs and pepper; mix well. Shape the mixture into 8 patties, about 2 1/2 inches wide.\n" +
                        "\n" +
                        "Step 4\n" +
                        "Heat remaining 1 1/2 teaspoons oil in the pan over medium heat. Add 4 patties and cook until the undersides are golden, 2 to 3 minutes. Using a wide spatula, turn them over onto the prepared baking sheet. Repeat with the remaining patties.\n" +
                        "\n" +
                        "Step 5\n" +
                        "Bake the salmon cakes until golden on top and heated through, 15 to 20 minutes. Meanwhile, prepare Creamy Dill Sauce. Serve salmon cakes with sauce and lemon wedges.");
        EatTable eatTable4 = new EatTable(4, "Muesli with Raspberries", "recipe010",
                "Top muesli with raspberries and serve with milk.",
                " Chipotle-Lime Cauliflower Taco Bowls", "recipe008",
                "Step 1\n" +
                        "Preheat oven to 450 degrees F. Line a large rimmed baking sheet with foil.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Combine lime juice, chipotles to taste, honey, garlic and salt in a blender. Process until mostly smooth. Place cauliflower in a large bowl; add the sauce and stir to coat. Transfer to the prepared baking sheet. Sprinkle onion over the cauliflower. Roast, stirring once, until the cauliflower is tender and browned in spots, 18 to 20 minutes; set aside to cool.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Divide quinoa among 4 single-serving lidded containers (1/2 cup each). Top each with one-fourth of the cauliflower mixture, 1/4 cup black beans and 2 tablespoons cheese. Seal the containers and refrigerate for up to 4 days.\n" +
                        "\n" +
                        "Step 4\n" +
                        "To reheat 1 container, vent the lid and microwave on High until steaming, 2 1/2 to 3 minutes. Top with 1/4 cup cabbage and 1/4 avocado (sliced). Serve with a lime wedge, if desired.",
                " Wraps with Peanut Sauce", "recipe012",
                "Step 1\n" +
                        "Whisk peanut butter, soy sauce, honey, water, and sesame oil in a small bowl.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Heat olive oil in a large nonstick skillet over medium heat. Add scallion whites, serrano, ginger, and garlic; cook until starting to soften, about 2 minutes. Add chicken; cook, breaking it up with a spoon or potato masher, until cooked through, 3 to 4 minutes.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Add the peanut sauce to the chicken mixture; cook until the sauce has thickened, about 3 minutes. Remove from heat. Stir in jicama and scallion greens.\n" +
                        "\n" +
                        "Step 4\n" +
                        "To serve, make 8 stacks of 2 lettuce leaves each. Divide rice, the chicken mixture, cucumber, and cilantro among the lettuce cups. Serve with lime wedges.");
        EatTable eatTable5 = new EatTable(5, "Egg", "recipe004",
                "Put two eggs in the boiled water for 10 minutes",
                "Chicken Taco", "recipe005",
                "Step 1\n" +
                        "Preheat oven to 450 degrees F. Line a large rimmed baking sheet with foil.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Combine lime juice, chipotles to taste, honey, garlic and salt in a blender. Process until mostly smooth. Place cauliflower in a large bowl; add the sauce and stir to coat. Transfer to the prepared baking sheet. Sprinkle onion over the cauliflower. Roast, stirring once, until the cauliflower is tender and browned in spots, 18 to 20 minutes; set aside to cool.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Divide quinoa among 4 single-serving lidded containers (1/2 cup each). Top each with one-fourth of the cauliflower mixture, 1/4 cup black beans and 2 tablespoons cheese. Seal the containers and refrigerate for up to 4 days.\n" +
                        "\n" +
                        "Step 4\n" +
                        "To reheat 1 container, vent the lid and microwave on High until steaming, 2 1/2 to 3 minutes. Top with 1/4 cup cabbage and 1/4 avocado (sliced). Serve with a lime wedge, if desired.",
                "Veggie Burger", "recipe006",
                "EatTable2Name3");
        EatTable eatTable6 = new EatTable(6, "Egg", "recipe004",
                "Put two eggs in the boiled water for 10 minutes",
                "Chipotle-Lime Cauliflower Taco Bowls", "recipe008",
                "Step 1\n" +
                        "Preheat oven to 450 degrees F. Line a large rimmed baking sheet with foil.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Combine lime juice, chipotles to taste, honey, garlic and salt in a blender. Process until mostly smooth. Place cauliflower in a large bowl; add the sauce and stir to coat. Transfer to the prepared baking sheet. Sprinkle onion over the cauliflower. Roast, stirring once, until the cauliflower is tender and browned in spots, 18 to 20 minutes; set aside to cool.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Divide quinoa among 4 single-serving lidded containers (1/2 cup each). Top each with one-fourth of the cauliflower mixture, 1/4 cup black beans and 2 tablespoons cheese. Seal the containers and refrigerate for up to 4 days.\n" +
                        "\n" +
                        "Step 4\n" +
                        "To reheat 1 container, vent the lid and microwave on High until steaming, 2 1/2 to 3 minutes. Top with 1/4 cup cabbage and 1/4 avocado (sliced). Serve with a lime wedge, if desired.",
                "Salmon Cakes", "recipe009",
                "Step 1\n" +
                        "Preheat oven to 450 degrees F. Coat a baking sheet with cooking spray.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Heat 1 1/2 teaspoons oil in a large nonstick skillet over medium-high heat. Add onion and celery; cook, stirring, until softened, about 3 minutes. Stir in parsley; remove from the heat.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Place salmon in a medium bowl. Flake apart with a fork; remove any bones and skin. Add egg and mustard; mix well. Add the onion mixture, breadcrumbs and pepper; mix well. Shape the mixture into 8 patties, about 2 1/2 inches wide.\n" +
                        "\n" +
                        "Step 4\n" +
                        "Heat remaining 1 1/2 teaspoons oil in the pan over medium heat. Add 4 patties and cook until the undersides are golden, 2 to 3 minutes. Using a wide spatula, turn them over onto the prepared baking sheet. Repeat with the remaining patties.\n" +
                        "\n" +
                        "Step 5\n" +
                        "Bake the salmon cakes until golden on top and heated through, 15 to 20 minutes. Meanwhile, prepare Creamy Dill Sauce. Serve salmon cakes with sauce and lemon wedges.");
        EatTable eatTable7 = new EatTable(7, "Muesli with Raspberries", "recipe010",
                "Top muesli with raspberries and serve with milk.",
                " Chipotle-Lime Cauliflower Taco Bowls", "recipe008",
                "Step 1\n" +
                        "Preheat oven to 450 degrees F. Line a large rimmed baking sheet with foil.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Combine lime juice, chipotles to taste, honey, garlic and salt in a blender. Process until mostly smooth. Place cauliflower in a large bowl; add the sauce and stir to coat. Transfer to the prepared baking sheet. Sprinkle onion over the cauliflower. Roast, stirring once, until the cauliflower is tender and browned in spots, 18 to 20 minutes; set aside to cool.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Divide quinoa among 4 single-serving lidded containers (1/2 cup each). Top each with one-fourth of the cauliflower mixture, 1/4 cup black beans and 2 tablespoons cheese. Seal the containers and refrigerate for up to 4 days.\n" +
                        "\n" +
                        "Step 4\n" +
                        "To reheat 1 container, vent the lid and microwave on High until steaming, 2 1/2 to 3 minutes. Top with 1/4 cup cabbage and 1/4 avocado (sliced). Serve with a lime wedge, if desired.",
                " Wraps with Peanut Sauce", "recipe012",
                "Step 1\n" +
                        "Whisk peanut butter, soy sauce, honey, water, and sesame oil in a small bowl.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Heat olive oil in a large nonstick skillet over medium heat. Add scallion whites, serrano, ginger, and garlic; cook until starting to soften, about 2 minutes. Add chicken; cook, breaking it up with a spoon or potato masher, until cooked through, 3 to 4 minutes.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Add the peanut sauce to the chicken mixture; cook until the sauce has thickened, about 3 minutes. Remove from heat. Stir in jicama and scallion greens.\n" +
                        "\n" +
                        "Step 4\n" +
                        "To serve, make 8 stacks of 2 lettuce leaves each. Divide rice, the chicken mixture, cucumber, and cilantro among the lettuce cups. Serve with lime wedges.");
        EatTable eatTable8 = new EatTable(8, "Egg in a Hole", "recipe022",
                "Step 1\n" +
                        "Slice tops and bottoms off bell peppers and finely dice. Remove and discard seeds and membranes. Slice each pepper into four 1/2-inch-thick rings.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Combine the diced pepper with avocado, onion, jalapeño, cilantro, tomatoes, lime juice, and 1/2 teaspoon salt in a medium bowl.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Heat 1 teaspoon oil in a large nonstick skillet over medium heat. Add 4 bell pepper rings, then crack 1 egg into the middle of each ring. Season with 1/8 teaspoon each salt and pepper. Cook until the whites are mostly set but the yolks are still runny, 2 to 3 minutes. Gently flip and cook 1 minute more for runny yolks, 1 1/2 to 2 minutes more for firmer yolks. Transfer to serving plates and repeat with the remaining pepper rings and eggs.\n" +
                        "\n" +
                        "Step 4\n" +
                        "Serve with the avocado salsa and garnish with additional cilantro, if desired.",
                "Salmon Salad-Stuffed Avocado", "recipe023",
                "Combine salmon with pesto, yogurt and shallot. Serve over avocado and baby spinach with crackers on the side.",
                "White-Bean Sage Cauliflower Gnocchi", "recipe024",
                "Step 1\n" +
                        "Heat butter and oil in a large skillet over medium-high heat. Add gnocchi and cook until browned, about 5 minutes.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Add beans, sage and water. Cover and simmer until the gnocchi are tender and the beans are heated through, about 5 minutes. Season with salt and pepper. Serve over arugula.");
        EatTable eatTable9 = new EatTable(9, "Mini Muffins", "recipe025",
                "Step 1\n" +
                        "Preheat oven to 350 degrees F. Coat a 24-cup mini muffin tin with cooking spray.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Pulse oats in a blender until finely ground. Add baking powder, baking soda and salt; pulse once or twice to blend. Add eggs, banana, brown sugar, oil and vanilla; puree until smooth. Stir in chocolate chips. Fill the prepared muffin cups.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Bake until a toothpick inserted in the center comes out clean, 15 to 17 minutes. Cool in the pan on a wire rack for 5 minutes, then turn out to cool completely.",
                "Chicken Satay Bowls", "recipe026",
                "Step 1\n" +
                        "Prepare Thai Chicken Satay with Spicy Peanut Sauce as directed. Remove the chicken from the skewers and cut into strips. Divide the sauce among 4 small condiment containers with lids and refrigerate until ready to use.\n" +
                        "\n" +
                        "Step 2\n" +
                        "To prepare slaw: Toss cabbage, bell pepper, carrots and green onion in a large bowl.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Divide the slaw among 4 single-serving containers with lids. Top each with one-fourth of the chicken and 1/2 tablespoon sesame seeds. Dress with the reserved sauce just before serving.",
                " Shrimp Scampi Zoodles", "recipe027",
                "Step 1\n" +
                        "Using a spiral vegetable slicer or a vegetable peeler, cut zucchini lengthwise into long, thin strands or strips. Place the zucchini noodles in a colander and toss with 1/4 teaspoon salt. Let drain for 15 to 30 minutes, then gently squeeze to remove any excess liquid.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Meanwhile, heat butter and 1 tablespoon oil in a large skillet over medium-high heat. Add garlic and cook, stirring, for 30 seconds. Carefully add wine and bring to a simmer. Add shrimp and cook, stirring, until the shrimp are pink and just cooked through, 3 to 4 minutes. Remove from heat and add lemon juice, parsley, pepper and the remaining 1/4 teaspoon salt; stir to combine. Transfer to a large bowl and set aside.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Heat the remaining 1 tablespoon oil in the skillet over medium-high heat. Add zucchini and gently toss until hot, about 3 minutes. Pour the shrimp mixture over the zucchini and gently toss to combine. Serve sprinkled with Parmesan and a squeeze of lemon.");
        EatTable eatTable10 = new EatTable(10, "Mini Muffins", "recipe025",
                "Step 1\n" +
                        "Preheat oven to 350 degrees F. Coat a 24-cup mini muffin tin with cooking spray.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Pulse oats in a blender until finely ground. Add baking powder, baking soda and salt; pulse once or twice to blend. Add eggs, banana, brown sugar, oil and vanilla; puree until smooth. Stir in chocolate chips. Fill the prepared muffin cups.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Bake until a toothpick inserted in the center comes out clean, 15 to 17 minutes. Cool in the pan on a wire rack for 5 minutes, then turn out to cool completely.",
                " Chicken Satay Bowls with Spicy Peanut Sauce", "recipe027",
                "Step 1\n" +
                        "Prepare Thai Chicken Satay with Spicy Peanut Sauce as directed. Remove the chicken from the skewers and cut into strips. Divide the sauce among 4 small condiment containers with lids and refrigerate until ready to use.\n" +
                        "\n" +
                        "Step 2\n" +
                        "To prepare slaw: Toss cabbage, bell pepper, carrots and green onion in a large bowl.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Divide the slaw among 4 single-serving containers with lids. Top each with one-fourth of the chicken and 1/2 tablespoon sesame seeds. Dress with the reserved sauce just before serving.",
                "Pork Paprikash with Cauliflower \"Rice\"", "recipe030",
                "Step 1\n" +
                        "Trim fat from meat. Cut meat into bite-size pieces; set aside.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Place cauliflower in a food processor. Cover and process with several on/off pulses until cauliflower is evenly chopped into rice-size pieces.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Heat 1 tablespoon of the oil in a very large nonstick skillet over medium-high heat. Add cauliflower and 1/8 teaspoon of the salt. Cook 8 to 10 minutes or until golden brown flecks appear throughout, stirring occasionally.\n" +
                        "\n" +
                        "Step 4\n" +
                        "Meanwhile, heat the remaining 1 tablespoon oil in a large skillet over medium-high heat. Add meat and onion; cook about 3 minutes or until meat is starting to brown, stirring occasionally. Sprinkle with 1 1/2 tablespoons paprika, ground pepper, and remaining 1/4 teaspoon salt. Cook and stir 1 minute more.\n" +
                        "\n" +
                        "Step 5\n" +
                        "Add tomatoes, broth, and banana peppers. Bring to boiling; reduce heat to medium-low. Cook, covered, 5 minutes. Increase heat to medium-high. Cook, uncovered, 4 to 6 minutes or until slightly thickened, stirring frequently. Stir together 1/3 cup sour cream and flour in a small bowl; stir into meat mixture. Cook and stir until thickened and bubbly.\n" +
                        "\n" +
                        "Step 6\n" +
                        "Serve meat mixture over cauliflower \"rice.\" If desired, top each serving with 2 teaspoons sour cream and a sprinkle of paprika.");
        EatTable eatTable11 = new EatTable(11, "Breakfast set", "recipe001",
                "½ cup nonfat plain Greek yogurt\n" +
                        "1 cup raspberries\n" +
                        "1 tsp. honey\n" +
                        "1 Tbsp. chia seeds",
                " Chicken Satay Bowls with Spicy Peanut Sauce", "recipe027",
                "Step 1\n" +
                        "Prepare Thai Chicken Satay with Spicy Peanut Sauce as directed. Remove the chicken from the skewers and cut into strips. Divide the sauce among 4 small condiment containers with lids and refrigerate until ready to use.\n" +
                        "\n" +
                        "Step 2\n" +
                        "To prepare slaw: Toss cabbage, bell pepper, carrots and green onion in a large bowl.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Divide the slaw among 4 single-serving containers with lids. Top each with one-fourth of the chicken and 1/2 tablespoon sesame seeds. Dress with the reserved sauce just before serving.",
                "Roasted Salmon", "recipe033",
                "Step 1\n" +
                        "Position racks in upper third and middle of oven; preheat to 425 degrees F.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Combine 1 tablespoon oil, paprika and 1/4 teaspoon salt in a medium bowl. Very thoroughly pat chickpeas dry, then toss with the paprika mixture. Spread on a rimmed baking sheet. Bake the chickpeas on the upper rack, stirring twice, for 30 minutes.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Meanwhile, puree buttermilk, mayonnaise, herbs, 1/4 teaspoon pepper and garlic powder in a blender until smooth. Set aside.\n" +
                        "\n" +
                        "Step 4\n" +
                        "Heat the remaining 1 tablespoon oil in a large skillet over medium heat. Add kale and cook, stirring occasionally, for 2 minutes. Add water and continue cooking until the kale is tender, about 5 minutes more. Remove from heat and stir in a pinch of salt.\n" +
                        "\n" +
                        "Step 5\n" +
                        "Remove the chickpeas from the oven and push them to one side of the pan. Place salmon on the other side and season with the remaining 1/4 teaspoon each salt and pepper. Bake until the salmon is just cooked through, 5 to 8 minutes.\n" +
                        "\n" +
                        "Step 6\n" +
                        "Drizzle the reserved dressing on the salmon, garnish with more herbs, if desired, and serve with the kale and chickpeas.");
        EatTable eatTable12 = new EatTable(12, "Egg in a Hole", "recipe022",
                "Step 1\n" +
                        "Slice tops and bottoms off bell peppers and finely dice. Remove and discard seeds and membranes. Slice each pepper into four 1/2-inch-thick rings.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Combine the diced pepper with avocado, onion, jalapeño, cilantro, tomatoes, lime juice, and 1/2 teaspoon salt in a medium bowl.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Heat 1 teaspoon oil in a large nonstick skillet over medium heat. Add 4 bell pepper rings, then crack 1 egg into the middle of each ring. Season with 1/8 teaspoon each salt and pepper. Cook until the whites are mostly set but the yolks are still runny, 2 to 3 minutes. Gently flip and cook 1 minute more for runny yolks, 1 1/2 to 2 minutes more for firmer yolks. Transfer to serving plates and repeat with the remaining pepper rings and eggs.\n" +
                        "\n" +
                        "Step 4\n" +
                        "Serve with the avocado salsa and garnish with additional cilantro, if desired.",
                "Salmon Salad-Stuffed Avocado", "recipe023",
                "Combine salmon with pesto, yogurt and shallot. Serve over avocado and baby spinach with crackers on the side.",
                "White-Bean Sage Cauliflower Gnocchi", "recipe024",
                "Step 1\n" +
                        "Heat butter and oil in a large skillet over medium-high heat. Add gnocchi and cook until browned, about 5 minutes.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Add beans, sage and water. Cover and simmer until the gnocchi are tender and the beans are heated through, about 5 minutes. Season with salt and pepper. Serve over arugula.");
        EatTable eatTable13 = new EatTable(13, "Mini Muffins", "recipe025",
                "Step 1\n" +
                        "Preheat oven to 350 degrees F. Coat a 24-cup mini muffin tin with cooking spray.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Pulse oats in a blender until finely ground. Add baking powder, baking soda and salt; pulse once or twice to blend. Add eggs, banana, brown sugar, oil and vanilla; puree until smooth. Stir in chocolate chips. Fill the prepared muffin cups.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Bake until a toothpick inserted in the center comes out clean, 15 to 17 minutes. Cool in the pan on a wire rack for 5 minutes, then turn out to cool completely.",
                "Chicken Satay Bowls", "recipe026",
                "Step 1\n" +
                        "Prepare Thai Chicken Satay with Spicy Peanut Sauce as directed. Remove the chicken from the skewers and cut into strips. Divide the sauce among 4 small condiment containers with lids and refrigerate until ready to use.\n" +
                        "\n" +
                        "Step 2\n" +
                        "To prepare slaw: Toss cabbage, bell pepper, carrots and green onion in a large bowl.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Divide the slaw among 4 single-serving containers with lids. Top each with one-fourth of the chicken and 1/2 tablespoon sesame seeds. Dress with the reserved sauce just before serving.",
                " Shrimp Scampi Zoodles", "recipe027",
                "Step 1\n" +
                        "Using a spiral vegetable slicer or a vegetable peeler, cut zucchini lengthwise into long, thin strands or strips. Place the zucchini noodles in a colander and toss with 1/4 teaspoon salt. Let drain for 15 to 30 minutes, then gently squeeze to remove any excess liquid.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Meanwhile, heat butter and 1 tablespoon oil in a large skillet over medium-high heat. Add garlic and cook, stirring, for 30 seconds. Carefully add wine and bring to a simmer. Add shrimp and cook, stirring, until the shrimp are pink and just cooked through, 3 to 4 minutes. Remove from heat and add lemon juice, parsley, pepper and the remaining 1/4 teaspoon salt; stir to combine. Transfer to a large bowl and set aside.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Heat the remaining 1 tablespoon oil in the skillet over medium-high heat. Add zucchini and gently toss until hot, about 3 minutes. Pour the shrimp mixture over the zucchini and gently toss to combine. Serve sprinkled with Parmesan and a squeeze of lemon.");
        EatTable eatTable14 = new EatTable(14, "Mini Muffins", "recipe025",
                "Step 1\n" +
                        "Preheat oven to 350 degrees F. Coat a 24-cup mini muffin tin with cooking spray.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Pulse oats in a blender until finely ground. Add baking powder, baking soda and salt; pulse once or twice to blend. Add eggs, banana, brown sugar, oil and vanilla; puree until smooth. Stir in chocolate chips. Fill the prepared muffin cups.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Bake until a toothpick inserted in the center comes out clean, 15 to 17 minutes. Cool in the pan on a wire rack for 5 minutes, then turn out to cool completely.",
                " Chicken Satay Bowls with Spicy Peanut Sauce", "recipe027",
                "Step 1\n" +
                        "Prepare Thai Chicken Satay with Spicy Peanut Sauce as directed. Remove the chicken from the skewers and cut into strips. Divide the sauce among 4 small condiment containers with lids and refrigerate until ready to use.\n" +
                        "\n" +
                        "Step 2\n" +
                        "To prepare slaw: Toss cabbage, bell pepper, carrots and green onion in a large bowl.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Divide the slaw among 4 single-serving containers with lids. Top each with one-fourth of the chicken and 1/2 tablespoon sesame seeds. Dress with the reserved sauce just before serving.",
                "Pork Paprikash with Cauliflower \"Rice\"", "recipe030",
                "Step 1\n" +
                        "Trim fat from meat. Cut meat into bite-size pieces; set aside.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Place cauliflower in a food processor. Cover and process with several on/off pulses until cauliflower is evenly chopped into rice-size pieces.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Heat 1 tablespoon of the oil in a very large nonstick skillet over medium-high heat. Add cauliflower and 1/8 teaspoon of the salt. Cook 8 to 10 minutes or until golden brown flecks appear throughout, stirring occasionally.\n" +
                        "\n" +
                        "Step 4\n" +
                        "Meanwhile, heat the remaining 1 tablespoon oil in a large skillet over medium-high heat. Add meat and onion; cook about 3 minutes or until meat is starting to brown, stirring occasionally. Sprinkle with 1 1/2 tablespoons paprika, ground pepper, and remaining 1/4 teaspoon salt. Cook and stir 1 minute more.\n" +
                        "\n" +
                        "Step 5\n" +
                        "Add tomatoes, broth, and banana peppers. Bring to boiling; reduce heat to medium-low. Cook, covered, 5 minutes. Increase heat to medium-high. Cook, uncovered, 4 to 6 minutes or until slightly thickened, stirring frequently. Stir together 1/3 cup sour cream and flour in a small bowl; stir into meat mixture. Cook and stir until thickened and bubbly.\n" +
                        "\n" +
                        "Step 6\n" +
                        "Serve meat mixture over cauliflower \"rice.\" If desired, top each serving with 2 teaspoons sour cream and a sprinkle of paprika.");
        EatTable eatTable15 = new EatTable(15, "Breakfast set", "recipe004",
                "1 3/4 cups whole grain fortified cereal\n" +
                        "1/4 cup slivered almonds\n" +
                        "1 cup unsweetened plain almond milk\n" +
                        "1 cup blueberries",
                "EatTable5Name2", "recipe044",
                "Step 1\n" +
                        "Bring water to a boil in a small saucepan. Stir in couscous and remove from the heat. Cover and let stand for 5 minutes. Fluff with a fork. Set aside.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Meanwhile, combine parsley, mint, lemon juice, oil, garlic, 1/8 teaspoon salt and pepper in a small bowl.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Toss chicken tenders in a medium bowl with 1 tablespoon of the parsley mixture and the remaining 1/8 teaspoon salt. Place the tenders in a large nonstick skillet and cook over medium heat until cooked though, 3 to 5 minutes per side. Transfer to a clean cutting board. Cut into bite-size pieces when cool enough to handle.\n" +
                        "\n" +
                        "Step 4\n" +
                        "Stir the remaining parsley mixture into the couscous along with tomato and cucumber.\n" +
                        "\n" +
                        "Step 5\n" +
                        "To assemble wraps, spread about 3/4 cup of the couscous mixture onto each wrap. Divide the chicken among the wraps. Roll the wraps up like a burrito, tucking in the sides to hold the ingredients in. Serve cut in half.",
                "One-Pot Pasta", "recipe045",
                "Combine pasta, shrimp, spinach, corn, peas, onion, garlic, tomato paste, paprika, saffron and salt in a large pot. Stir in water. Bring to a boil over high heat. Boil, stirring frequently, until the pasta is cooked and the water has almost evaporated, 10 to 12 minutes. Remove from heat and let stand, stirring occasionally, for 5 minutes. Serve sprinkled with parsley.");
        EatTable eatTable16 = new EatTable(16, "Mix milk", "recipe046",
                "1 servingPeanut Butter-Strawberry-Kale Smoothiemixed with 1 tablespoon ground flax seed.\n" +
                        "2 slices whole-wheat bread\n" +
                        "1/2 avocado, mashed",
                "English Muffin Pesto Pizza", "recipe001",
                "Split English muffin in half. Spread pesto evenly over each half. Top each half with spinach, tomato, and cheese. Broil in oven (or toaster oven) until cheese is melted, about 3-5 minutes",
                "Summer Vegetable Chicken Tortilla Casserole", "recipe048",
                "Step 1\n" +
                        "Arrange racks in top and bottom third of oven; preheat to 450 degrees F. Coat two rimmed baking sheets and a 9-by-13-inch baking pan with cooking spray.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Combine eggplant, onions, corn, 1 1/2 tablespoons oil, 1/4 teaspoon salt and 1/4 teaspoon pepper in a medium bowl. Transfer to one of the prepared baking sheets (reserve the bowl). Roast on the bottom rack for 10 minutes. Combine chicken, zucchini (or squash), garlic and the remaining 1 1/2 tablespoons oil, 1/4 teaspoon salt and 1/4 teaspoon pepper in the reserved bowl. Transfer to the second prepared baking sheet.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Move the baking sheet with the eggplant to the top rack and place the baking sheet with the chicken on the bottom rack. Roast until the chicken is cooked through and all the vegetables are soft and starting to brown, about 15 minutes. Shred the chicken and transfer it along with the vegetables to a large bowl; stir in 1 cup enchilada sauce. Reduce oven temperature to 400 degrees F.\n" +
                        "\n" +
                        "Step 4\n" +
                        "Arrange 6 tortilla halves on the bottom of the prepared baking pan. Top with 1 1/2 cups of the chicken mixture, then 1/2 cup cheese. Repeat with 3 more layers of tortillas and 2 more layers of the chicken mixture and cheese. Top the top layer of tortillas with the remaining 1/4 cup enchilada sauce and 1/2 cup cheese. Bake until edges are bubbling and cheese is starting to brown, 20 to 25 minutes. Let cool for 10 minutes before serving. Garnish with cilantro and serve with lime wedges, if desired.");
        EatTable eatTable17 = new EatTable(17, "Breakfast set", "recipe049",
                "3/4 cup oats cooked in 1 cup low-fat milk and 1/2 cup water\n" +
                        "1 tsp. maple syrup\n" +
                        "1/4 tsp. cinnamon\n" +
                        "2 Tbsp. chopped walnuts",
                "Super-Green Edamame Salad", "recipe050",
                "Step 1\n" +
                        "Combine edamame, pink beans (or pinto beans), bell pepper and chives in a large bowl.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Combine spinach, avocado, apple juice, oil, lemon juice, tamari (or soy sauce), salt and pepper in a blender. Puree until smooth and creamy. Add the dressing to the bean mixture and stir to coat. Garnish with more chives, if desired. Serve at room temperature or cold.",
                "Blackberry BBQ Pork Chops ", "recipe051",
                "Step 1\n" +
                        "Position rack in upper third of oven; preheat broiler to high.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Heat 1 tablespoon oil and bacon in a large pot over medium-high heat; cook, stirring often, until the bacon starts to brown, 1 to 2 minutes. Reduce heat to medium. Stir in collard greens (or kale) and 1/4 teaspoon each salt and pepper. Cover and cook for 5 minutes. Stir the greens and place corn on top of them. Cover and cook until the corn is tender-crisp, about 5 minutes more. Set aside, covered.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Meanwhile, combine honey, ketchup, garlic powder, vinegar and ginger in a small bowl. Add blackberries and coarsely mash, stirring to combine.\n" +
                        "\n" +
                        "Step 4\n" +
                        "Heat the remaining 2 teaspoons oil in a medium skillet over medium-high heat until hot but not smoking. Season pork chops with the remaining 1/4 teaspoon each salt and pepper. Cook until browned on the bottom, 2 to 3 minutes. Turn the chops and top with the sauce.\n" +
                        "\n" +
                        "Step 5\n" +
                        "Transfer the pan to the oven. Broil until an instant-read thermometer inserted in the center of a chop registers 145 degrees F, 6 to 8 minutes. Serve the pork with the greens and corn.");
        EatTable eatTable18 = new EatTable(18, "Berry Kefir Smoothie", "recipe052",
                "Combine berries, kefir, banana, almond butter and vanilla in a blender. Blend until smooth.",
                "Mix bowl", "recipe027",
                "2 corn tortillas\n" +
                        "2/3 cup no-salt-added canned black beans\n" +
                        "1 avocado, sliced\n" +
                        "3 Tbsp. shredded cheddar cheese\n" +
                        "1 cup lettuce, shredded\n" +
                        "2 Tbsp. pico de gallo",
                "Two-Cheese Fusilli ", "recipe054",
                "Step 1\n" +
                        "Put a large pot of water on to boil.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Combine tomatoes, vinegar, garlic and salt in a large bowl. Set aside to marinate.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Cook pasta according to package directions. Reserve 1/2 cup pasta water, then drain. Immediately return the pasta to the pot. Add fontina and the reserved cooking liquid; stir constantly until the cheese is melted. Fold in ricotta. Transfer to a serving bowl or individual pasta bowls.\n" +
                        "\n" +
                        "Step 4\n" +
                        "Using a slotted spoon, spoon the marinated tomatoes over the pasta. (Discard the marinating liquid.) Drizzle with oil and top with basil and a generous grinding of pepper.");
        EatTable eatTable19 = new EatTable(15, "Breakfast set", "recipe004",
                "1 3/4 cups whole grain fortified cereal\n" +
                        "1/4 cup slivered almonds\n" +
                        "1 cup unsweetened plain almond milk\n" +
                        "1 cup blueberries",
                "EatTable5Name2", "recipe044",
                "Step 1\n" +
                        "Bring water to a boil in a small saucepan. Stir in couscous and remove from the heat. Cover and let stand for 5 minutes. Fluff with a fork. Set aside.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Meanwhile, combine parsley, mint, lemon juice, oil, garlic, 1/8 teaspoon salt and pepper in a small bowl.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Toss chicken tenders in a medium bowl with 1 tablespoon of the parsley mixture and the remaining 1/8 teaspoon salt. Place the tenders in a large nonstick skillet and cook over medium heat until cooked though, 3 to 5 minutes per side. Transfer to a clean cutting board. Cut into bite-size pieces when cool enough to handle.\n" +
                        "\n" +
                        "Step 4\n" +
                        "Stir the remaining parsley mixture into the couscous along with tomato and cucumber.\n" +
                        "\n" +
                        "Step 5\n" +
                        "To assemble wraps, spread about 3/4 cup of the couscous mixture onto each wrap. Divide the chicken among the wraps. Roll the wraps up like a burrito, tucking in the sides to hold the ingredients in. Serve cut in half.",
                "One-Pot Pasta", "recipe045",
                "Combine pasta, shrimp, spinach, corn, peas, onion, garlic, tomato paste, paprika, saffron and salt in a large pot. Stir in water. Bring to a boil over high heat. Boil, stirring frequently, until the pasta is cooked and the water has almost evaporated, 10 to 12 minutes. Remove from heat and let stand, stirring occasionally, for 5 minutes. Serve sprinkled with parsley.");
        EatTable eatTable20 = new EatTable(16, "Mix milk", "recipe046",
                "1 servingPeanut Butter-Strawberry-Kale Smoothiemixed with 1 tablespoon ground flax seed.\n" +
                        "2 slices whole-wheat bread\n" +
                        "1/2 avocado, mashed",
                "English Muffin Pesto Pizza", "recipe001",
                "Split English muffin in half. Spread pesto evenly over each half. Top each half with spinach, tomato, and cheese. Broil in oven (or toaster oven) until cheese is melted, about 3-5 minutes",
                "Summer Vegetable Chicken Tortilla Casserole", "recipe048",
                "Step 1\n" +
                        "Arrange racks in top and bottom third of oven; preheat to 450 degrees F. Coat two rimmed baking sheets and a 9-by-13-inch baking pan with cooking spray.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Combine eggplant, onions, corn, 1 1/2 tablespoons oil, 1/4 teaspoon salt and 1/4 teaspoon pepper in a medium bowl. Transfer to one of the prepared baking sheets (reserve the bowl). Roast on the bottom rack for 10 minutes. Combine chicken, zucchini (or squash), garlic and the remaining 1 1/2 tablespoons oil, 1/4 teaspoon salt and 1/4 teaspoon pepper in the reserved bowl. Transfer to the second prepared baking sheet.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Move the baking sheet with the eggplant to the top rack and place the baking sheet with the chicken on the bottom rack. Roast until the chicken is cooked through and all the vegetables are soft and starting to brown, about 15 minutes. Shred the chicken and transfer it along with the vegetables to a large bowl; stir in 1 cup enchilada sauce. Reduce oven temperature to 400 degrees F.\n" +
                        "\n" +
                        "Step 4\n" +
                        "Arrange 6 tortilla halves on the bottom of the prepared baking pan. Top with 1 1/2 cups of the chicken mixture, then 1/2 cup cheese. Repeat with 3 more layers of tortillas and 2 more layers of the chicken mixture and cheese. Top the top layer of tortillas with the remaining 1/4 cup enchilada sauce and 1/2 cup cheese. Bake until edges are bubbling and cheese is starting to brown, 20 to 25 minutes. Let cool for 10 minutes before serving. Garnish with cilantro and serve with lime wedges, if desired.");
        EatTable eatTable21 = new EatTable(17, "Breakfast set", "recipe049",
                "3/4 cup oats cooked in 1 cup low-fat milk and 1/2 cup water\n" +
                        "1 tsp. maple syrup\n" +
                        "1/4 tsp. cinnamon\n" +
                        "2 Tbsp. chopped walnuts",
                "Super-Green Edamame Salad", "recipe050",
                "Step 1\n" +
                        "Combine edamame, pink beans (or pinto beans), bell pepper and chives in a large bowl.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Combine spinach, avocado, apple juice, oil, lemon juice, tamari (or soy sauce), salt and pepper in a blender. Puree until smooth and creamy. Add the dressing to the bean mixture and stir to coat. Garnish with more chives, if desired. Serve at room temperature or cold.",
                "Blackberry BBQ Pork Chops ", "recipe051",
                "Step 1\n" +
                        "Position rack in upper third of oven; preheat broiler to high.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Heat 1 tablespoon oil and bacon in a large pot over medium-high heat; cook, stirring often, until the bacon starts to brown, 1 to 2 minutes. Reduce heat to medium. Stir in collard greens (or kale) and 1/4 teaspoon each salt and pepper. Cover and cook for 5 minutes. Stir the greens and place corn on top of them. Cover and cook until the corn is tender-crisp, about 5 minutes more. Set aside, covered.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Meanwhile, combine honey, ketchup, garlic powder, vinegar and ginger in a small bowl. Add blackberries and coarsely mash, stirring to combine.\n" +
                        "\n" +
                        "Step 4\n" +
                        "Heat the remaining 2 teaspoons oil in a medium skillet over medium-high heat until hot but not smoking. Season pork chops with the remaining 1/4 teaspoon each salt and pepper. Cook until browned on the bottom, 2 to 3 minutes. Turn the chops and top with the sauce.\n" +
                        "\n" +
                        "Step 5\n" +
                        "Transfer the pan to the oven. Broil until an instant-read thermometer inserted in the center of a chop registers 145 degrees F, 6 to 8 minutes. Serve the pork with the greens and corn.");
        EatTable eatTable22 = new EatTable(22, "Breakfast set", "recipe064",
                "3/4 cup oatmeal cooked in 1 1/2 cup water\n" +
                        "1/3 cup raspberries\n" +
                        "1 medium apple",
                "Whole-Wheat Veggie Wrap", "recipe065",
                "Lay tortilla on work surface. Spread hummus and avocado on the tortilla. Add veggies and Cheddar and roll up. Cut in half before serving.",
                "Mushroom-Quinoa Veggie Burgers", "recipe066",
                "Step 1\n" +
                        "Place mushroom, black beans, almond butter, 1 tablespoon mayonnaise, pepper, paprika, 1/2 teaspoon garlic powder and salt in a food processor. Pulse, stopping once or twice to scrape down the sides, until a coarse mixture forms that holds together when pressed. Transfer to a bowl and add quinoa and oats; stir well to combine. Refrigerate for 1 hour.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Meanwhile, whisk ketchup, mustard and the remaining 2 tablespoons mayonnaise and 1/4 teaspoon garlic powder in a small bowl until smooth.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Shape the mushroom mixture into 4 patties.\n" +
                        "\n" +
                        "Step 4\n" +
                        "Heat oil in a large grill pan or nonstick skillet over medium-high heat. Add the patties and cook until golden and beginning to crisp, 4 to 5 minutes. Carefully flip and cook until golden brown, 2 to 4 minutes more.\n" +
                        "\n" +
                        "Step 5\n" +
                        "Serve the burgers on buns with the special sauce, lettuce, tomato and onion.");
        EatTable eatTable23 = new EatTable(23, " Baked Banana-Nut Oatmeal Cups", "recipe067",
                "Step 1\n" +
                        "Preheat oven to 375 degrees F. Coat a muffin tin with cooking spray.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Combine oats, milk, bananas, brown sugar, eggs, baking powder, cinnamon, vanilla and salt in a large bowl. Fold in pecans. Divide the mixture among the muffin cups (about 1/3 cup each). Bake until a toothpick inserted in the center comes out clean, about 25 minutes. Cool in the pan for 10 minutes, then turn out onto a wire rack. Serve warm or at room temperature.",
                "Vegetable Hummus Bowls", "recipe068",
                "Step 1\n" +
                        "Preheat oven to 425 degrees F. Combine cauliflower, broccoli and garlic on a rimmed baking sheet. Drizzle with oil and sprinkle with oregano and salt; stir to coat. Roast for 10 minutes.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Add bell pepper and zucchini to the vegetables in the pan; stir to combine. Roast until the vegetables are crisp-tender and lightly browned, 10 to 15 minutes more. Sprinkle lemon zest over the vegetables; set aside to cool before assembling bowls.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Divide the roasted vegetables among 4 single-serving containers. Top each with 1/2 cup quinoa and 1/4 cup hummus and add a lemon wedge to each container. Seal the containers and refrigerate for up to 4 days. To serve, squeeze the lemon wedge over the bowl and top with one-fourth avocado, diced.",
                "Butternut Squash & Black Bean Tostadas", "recipe069",
                "Step 1\n" +
                        "Bring about 1 inch of water to a boil in a large saucepan fitted with a steamer basket. Add squash, cover and steam until very tender, about 15 minutes. Drain and return to the pan. Add 1 1/2 teaspoons chile powder and salt. Mash until mostly smooth; cover to keep warm.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Meanwhile, combine beans, scallions, 2 tablespoons lime juice, 1 tablespoon oil, cumin and the remaining 1/2 teaspoon chile powder in a medium bowl. Toss lettuce with the remaining 1 tablespoon each lime juice and oil in another bowl.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Spread about 1/4 cup squash on each tostada. Top each with about 3 tablespoons of the bean mixture, 1/4 cup lettuce and 1 tablespoon cheese. Sprinkle with pepitas.");
        EatTable eatTable24 = new EatTable(24, "Baked Banana-Nut Oatmeal Cups", "recipe070",
                "Step 1\n" +
                        "Preheat oven to 375 degrees F. Coat a muffin tin with cooking spray.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Combine oats, milk, bananas, brown sugar, eggs, baking powder, cinnamon, vanilla and salt in a large bowl. Fold in pecans. Divide the mixture among the muffin cups (about 1/3 cup each). Bake until a toothpick inserted in the center comes out clean, about 25 minutes. Cool in the pan for 10 minutes, then turn out onto a wire rack. Serve warm or at room temperature.",
                "Vegetable Hummus Bowls", "recipe071",
                "Step 1\n" +
                        "Preheat oven to 425 degrees F. Combine cauliflower, broccoli and garlic on a rimmed baking sheet. Drizzle with oil and sprinkle with oregano and salt; stir to coat. Roast for 10 minutes.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Add bell pepper and zucchini to the vegetables in the pan; stir to combine. Roast until the vegetables are crisp-tender and lightly browned, 10 to 15 minutes more. Sprinkle lemon zest over the vegetables; set aside to cool before assembling bowls.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Divide the roasted vegetables among 4 single-serving containers. Top each with 1/2 cup quinoa and 1/4 cup hummus and add a lemon wedge to each container. Seal the containers and refrigerate for up to 4 days. To serve, squeeze the lemon wedge over the bowl and top with one-fourth avocado, diced.",
                "One-Pot Tomato Basil Pasta", "recipe072",
                "Combine pasta, water, broth, tomatoes, oil, Italian seasoning, onion powder, garlic powder, salt and crushed red pepper in a large pot. Cover and bring to a boil over high heat. Uncover, reduce heat to medium-high and cook, stirring frequently, for 5 minutes. Stir in kale and cook, stirring often, until most of the liquid has been absorbed, 5 to 7 minutes more. (If using spinach, add it after about 10 minutes, so it cooks in the remaining 2 to 3 minutes.) Stir in basil. Garnish with Parmesan, if desired.");
        EatTable eatTable25 = new EatTable(25, "Breakfast set", "recipe051",
                "3/4 cup oatmeal cooked in 1 1/2 cup water\n" +
                        "1/3 cup raspberries",
                " Whole-Wheat Veggie Wrap", "recipe074",
                "Lay tortilla on work surface. Spread hummus and avocado on the tortilla. Add veggies and Cheddar and roll up. Cut in half before serving.",
                " Curried Chickpea Stew", "recipe075",
                "Step 1\n" +
                        "Place spinach (or other greens) in a microwave-safe dish; add 1 Tbsp. water and cover. Microwave on High, stirring occasionally, until just wilted, 1 to 2 minutes. Transfer to a colander to drain. When cool enough to handle, squeeze out any excess water. Coarsely chop and set aside.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Heat oil in a large nonstick skillet with high sides or a Dutch oven. Add onion and cook, stirring, until translucent, about 8 minutes. Add ginger, jalapeño, garlic, and curry powder; cook, stirring, for 30 seconds. Add carrots and 2 Tbsp. water; cover and cook, stirring occasionally, until the carrots have softened, about 10 minutes (add more water if the mixture becomes dry). Add cauliflower; cover and cook, stirring occasionally, until barely tender-crisp, 5 to 10 minutes more.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Stir in chickpeas, tomatoes, half-and-half, and coconut milk. Bring to just below boiling. Reduce heat to low and simmer uncovered, stirring occasionally, for 15 minutes. Stir in the reserved spinach (or greens) and heat through.\n" +
                        "\n" +
                        "Step 4\n" +
                        "Transfer half of the mixture (about 5 cups) to a 1 1/2-qt. freezer container; label and freeze for up to 1 month. Serve the remaining half at once, or refrigerate for up to 3 days.");
        EatTable eatTable26 = new EatTable(22, "Breakfast set", "recipe064",
                "3/4 cup oatmeal cooked in 1 1/2 cup water\n" +
                        "1/3 cup raspberries\n" +
                        "1 medium apple",
                "Whole-Wheat Veggie Wrap", "recipe065",
                "Lay tortilla on work surface. Spread hummus and avocado on the tortilla. Add veggies and Cheddar and roll up. Cut in half before serving.",
                "Mushroom-Quinoa Veggie Burgers", "recipe066",
                "Step 1\n" +
                        "Place mushroom, black beans, almond butter, 1 tablespoon mayonnaise, pepper, paprika, 1/2 teaspoon garlic powder and salt in a food processor. Pulse, stopping once or twice to scrape down the sides, until a coarse mixture forms that holds together when pressed. Transfer to a bowl and add quinoa and oats; stir well to combine. Refrigerate for 1 hour.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Meanwhile, whisk ketchup, mustard and the remaining 2 tablespoons mayonnaise and 1/4 teaspoon garlic powder in a small bowl until smooth.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Shape the mushroom mixture into 4 patties.\n" +
                        "\n" +
                        "Step 4\n" +
                        "Heat oil in a large grill pan or nonstick skillet over medium-high heat. Add the patties and cook until golden and beginning to crisp, 4 to 5 minutes. Carefully flip and cook until golden brown, 2 to 4 minutes more.\n" +
                        "\n" +
                        "Step 5\n" +
                        "Serve the burgers on buns with the special sauce, lettuce, tomato and onion.");
        EatTable eatTable27 = new EatTable(23, " Baked Banana-Nut Oatmeal Cups", "recipe067",
                "Step 1\n" +
                        "Preheat oven to 375 degrees F. Coat a muffin tin with cooking spray.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Combine oats, milk, bananas, brown sugar, eggs, baking powder, cinnamon, vanilla and salt in a large bowl. Fold in pecans. Divide the mixture among the muffin cups (about 1/3 cup each). Bake until a toothpick inserted in the center comes out clean, about 25 minutes. Cool in the pan for 10 minutes, then turn out onto a wire rack. Serve warm or at room temperature.",
                "Vegetable Hummus Bowls", "recipe068",
                "Step 1\n" +
                        "Preheat oven to 425 degrees F. Combine cauliflower, broccoli and garlic on a rimmed baking sheet. Drizzle with oil and sprinkle with oregano and salt; stir to coat. Roast for 10 minutes.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Add bell pepper and zucchini to the vegetables in the pan; stir to combine. Roast until the vegetables are crisp-tender and lightly browned, 10 to 15 minutes more. Sprinkle lemon zest over the vegetables; set aside to cool before assembling bowls.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Divide the roasted vegetables among 4 single-serving containers. Top each with 1/2 cup quinoa and 1/4 cup hummus and add a lemon wedge to each container. Seal the containers and refrigerate for up to 4 days. To serve, squeeze the lemon wedge over the bowl and top with one-fourth avocado, diced.",
                "Butternut Squash & Black Bean Tostadas", "recipe069",
                "Step 1\n" +
                        "Bring about 1 inch of water to a boil in a large saucepan fitted with a steamer basket. Add squash, cover and steam until very tender, about 15 minutes. Drain and return to the pan. Add 1 1/2 teaspoons chile powder and salt. Mash until mostly smooth; cover to keep warm.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Meanwhile, combine beans, scallions, 2 tablespoons lime juice, 1 tablespoon oil, cumin and the remaining 1/2 teaspoon chile powder in a medium bowl. Toss lettuce with the remaining 1 tablespoon each lime juice and oil in another bowl.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Spread about 1/4 cup squash on each tostada. Top each with about 3 tablespoons of the bean mixture, 1/4 cup lettuce and 1 tablespoon cheese. Sprinkle with pepitas.");
        EatTable eatTable28 = new EatTable(24, "Baked Banana-Nut Oatmeal Cups", "recipe070",
                "Step 1\n" +
                        "Preheat oven to 375 degrees F. Coat a muffin tin with cooking spray.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Combine oats, milk, bananas, brown sugar, eggs, baking powder, cinnamon, vanilla and salt in a large bowl. Fold in pecans. Divide the mixture among the muffin cups (about 1/3 cup each). Bake until a toothpick inserted in the center comes out clean, about 25 minutes. Cool in the pan for 10 minutes, then turn out onto a wire rack. Serve warm or at room temperature.",
                "Vegetable Hummus Bowls", "recipe071",
                "Step 1\n" +
                        "Preheat oven to 425 degrees F. Combine cauliflower, broccoli and garlic on a rimmed baking sheet. Drizzle with oil and sprinkle with oregano and salt; stir to coat. Roast for 10 minutes.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Add bell pepper and zucchini to the vegetables in the pan; stir to combine. Roast until the vegetables are crisp-tender and lightly browned, 10 to 15 minutes more. Sprinkle lemon zest over the vegetables; set aside to cool before assembling bowls.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Divide the roasted vegetables among 4 single-serving containers. Top each with 1/2 cup quinoa and 1/4 cup hummus and add a lemon wedge to each container. Seal the containers and refrigerate for up to 4 days. To serve, squeeze the lemon wedge over the bowl and top with one-fourth avocado, diced.",
                "One-Pot Tomato Basil Pasta", "recipe072",
                "Combine pasta, water, broth, tomatoes, oil, Italian seasoning, onion powder, garlic powder, salt and crushed red pepper in a large pot. Cover and bring to a boil over high heat. Uncover, reduce heat to medium-high and cook, stirring frequently, for 5 minutes. Stir in kale and cook, stirring often, until most of the liquid has been absorbed, 5 to 7 minutes more. (If using spinach, add it after about 10 minutes, so it cooks in the remaining 2 to 3 minutes.) Stir in basil. Garnish with Parmesan, if desired.");

        EatTable eatTable29 = new EatTable(29, "All Greens Smoothie Bowl", "recipe076",
                "Step 1\n" +
                        "Combine the banana, pear, apple, spinach and watercress in a blender. Blend until smooth, adding almond milk to achieve the desired consistency.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Divide evenly between 2 bowls and top with the kiwi, apple, pumpkin seeds and coconut flakes.",
                "White Bean & Veggie Salad", "recipe077",
                "Step 1\n" +
                        "Combine greens, veggies, beans and avocado in a medium bowl. Drizzle with vinegar and oil and season with salt and pepper. Toss to combine and transfer to a large plate.",
                "Roasted Chicken Thighs", "recipe078",
                "Step 1\n" +
                        "Preheat oven to 450 degrees F.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Toss potatoes and scallions with 4 teaspoons oil and 1/4 teaspoon each pepper and salt in a large bowl. Spread evenly on a large rimmed baking sheet. Place chicken on top; drizzle with 2 teaspoons oil and sprinkle with 1/4 teaspoon each pepper and salt. Roast until an instant-read thermometer inserted into the thickest part registers 165 degrees F and the potatoes are tender, 18 to 20 minutes.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Meanwhile, whisk the remaining 2 tablespoons oil and 1/4 teaspoon pepper with vinegar, herbs, mustard and shallot in a small bowl. Serve drizzled over the chicken and vegetables.");
        EatTable eatTable30 = new EatTable(30, "Steel-Cut Oatmeal", "recipe079",
                "Step 1\n" +
                        "Combine water (or milk) and salt in a small saucepan. Bring to a boil. Stir in oats and reduce heat to low; cook, stirring occasionally, until the oats are the desired texture, 20 to 30 minutes.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Serve with your favorite toppings, such as milk, sweetener, cinnamon, dried fruits or nuts.",
                "Creamy Tomato Cup-of-Soup", "recipe080",
                "Whisk tomatoes, broth and cream cheese in a large heatproof mug. Microwave on High, stirring occasionally, until heated through and creamy, about 2 minutes.",
                "Cajun Salmon", "recipe081",
                "Step 1\n" +
                        "Bring fish to room temperature by letting it stand on the counter for 15 minutes.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Meanwhile, whisk together yogurt, shallot, parsley, vinegar, horseradish, mustard, 1/4 tsp. paprika, 1/8 tsp. garlic powder, and a pinch each of salt and pepper in a small bowl. Cover and refrigerate until ready to use.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Pat both sides of the fish dry with a paper towel. Brush both sides with 2 tsp. oil. Season both sides evenly with the remaining 1/4 tsp. each salt and garlic powder, and 1/8 tsp. each paprika and pepper.\n" +
                        "\n" +
                        "Step 4\n" +
                        "Heat the remaining 1 tsp. oil in a large nonstick skillet over medium-high heat. When hot, add the fish, skinned-side up. Cook, pressing down on the fish with a spatula, but otherwise not moving the fillets, until the undersides are golden brown, about 5 minutes.\n" +
                        "\n" +
                        "Step 5\n" +
                        "Using the spatula, very carefully flip the fillets. Continue cooking, without moving, until the undersides are golden brown and the fish is opaque and just beginning to flake, another 2 to 3 minutes. Serve immediately, with the remoulade.");
        EatTable eatTable31 = new EatTable(31, "Baked Omelet Muffins", "recipe082",
                "Step 1\n" +
                        "Preheat oven to 325 degrees F. Coat a 12-cup muffin tin with cooking spray.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Cook bacon in a large skillet over medium heat until crisp, 4 to 5 minutes. Remove with a slotted spoon to a paper towel-lined plate, leaving the bacon fat in the pan. Add broccoli and scallions and cook, stirring, until soft, about 5 minutes. Remove from heat and let cool for 5 minutes.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Meanwhile, whisk eggs, cheese, milk, salt and pepper in a large bowl. Stir in the bacon and broccoli mixture. Divide the egg mixture among the prepared muffin cups.\n" +
                        "\n" +
                        "Step 4\n" +
                        "Bake until firm to the touch, 25 to 30 minutes. Let stand for 5 minutes before removing from the muffin tin.",
                "Zucchini Noodles", "recipe083",
                "Step 1\n" +
                        "Prepare Quick Turkey Meat Sauce as directed.\n" +
                        "\n" +
                        "Step 2\n" +
                        "As the sauce cooks, divide zucchini noodles among 4 single-serving containers with lids (about 2 cups per container).\n" +
                        "\n" +
                        "Step 3\n" +
                        "Add 3/4 cup of the sauce and 2 tablespoons Parmesan to each container. Seal and refrigerate for up to 4 days.\n" +
                        "\n" +
                        "Step 4\n" +
                        "To reheat, vent the lid and microwave on High until the sauce is steaming and the noodles are tender, 2 1/2 to 3 minutes.",
                "Vegetarian Nicoise Salad", "recipe084",
                "Step 1\n" +
                        "Toss salad greens with 1 Tbsp. plus 1 tsp. vinaigrette and place on a 9-inch plate.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Gently toss green beans and potatoes with the remaining 2 tsp. vinaigrette; arrange on top of the salad greens. Top with tomatoes, egg, olives, and feta and serve.");
        EatTable eatTable32 = new EatTable(32, "Steel-Cut Oats", "recipe085",
                "Step 1\n" +
                        "Combine water (or milk) and salt in a small saucepan. Bring to a boil. Stir in oats and reduce heat to low; cook, stirring occasionally, until the oats are the desired texture, 20 to 30 minutes.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Serve with your favorite toppings, such as milk, sweetener, cinnamon, dried fruits or nuts.",
                "Zucchini Noodles", "recipe086",
                "Step 1\n" +
                        "Prepare Quick Turkey Meat Sauce as directed.\n" +
                        "\n" +
                        "Step 2\n" +
                        "As the sauce cooks, divide zucchini noodles among 4 single-serving containers with lids (about 2 cups per container).\n" +
                        "\n" +
                        "Step 3\n" +
                        "Add 3/4 cup of the sauce and 2 tablespoons Parmesan to each container. Seal and refrigerate for up to 4 days.\n" +
                        "\n" +
                        "Step 4\n" +
                        "To reheat, vent the lid and microwave on High until the sauce is steaming and the noodles are tender, 2 1/2 to 3 minutes.",
                "Polenta Bowls", "recipe087",
                "Step 1\n" +
                        "Preheat oven to 425 degrees F. Line a large rimmed baking sheet with foil. Place shallots on the prepared pan; drizzle with 1 tablespoon oil and toss to coat. Roast until lightly browned, about 12 minutes.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Add asparagus, mushrooms, vinegar, thyme, pepper, 1/4 teaspoon salt and 1 tablespoon oil to the pan with the shallots. Stir to coat. Roast until the vegetables are just tender, about 8 minutes.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Meanwhile, combine milk and stock in a large saucepan; bring to a boil over medium-high heat. Whisk in polenta; reduce heat to medium-low, and cook, whisking often, until thickened, 4 to 5 minutes. Remove from heat; stir in Parmesan.\n" +
                        "\n" +
                        "Step 4\n" +
                        "Heat the remaining 1 tablespoon oil in a large nonstick skillet over medium-high heat. Add eggs, 1 at a time; cook, using a rubber spatula to keep the eggs separate, until the whites are completely cooked but the yolks are still slightly runny, 2 to 3 minutes.\n" +
                        "\n" +
                        "Step 5\n" +
                        "Spoon the polenta into 4 shallow bowls; top with the vegetables and eggs and sprinkle with the remaining 1/4 teaspoon salt.");
        EatTable eatTable33 = new EatTable(29, "All Greens Smoothie Bowl", "recipe076",
                "Step 1\n" +
                        "Combine the banana, pear, apple, spinach and watercress in a blender. Blend until smooth, adding almond milk to achieve the desired consistency.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Divide evenly between 2 bowls and top with the kiwi, apple, pumpkin seeds and coconut flakes.",
                "White Bean & Veggie Salad", "recipe077",
                "Step 1\n" +
                        "Combine greens, veggies, beans and avocado in a medium bowl. Drizzle with vinegar and oil and season with salt and pepper. Toss to combine and transfer to a large plate.",
                "Roasted Chicken Thighs", "recipe078",
                "Step 1\n" +
                        "Preheat oven to 450 degrees F.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Toss potatoes and scallions with 4 teaspoons oil and 1/4 teaspoon each pepper and salt in a large bowl. Spread evenly on a large rimmed baking sheet. Place chicken on top; drizzle with 2 teaspoons oil and sprinkle with 1/4 teaspoon each pepper and salt. Roast until an instant-read thermometer inserted into the thickest part registers 165 degrees F and the potatoes are tender, 18 to 20 minutes.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Meanwhile, whisk the remaining 2 tablespoons oil and 1/4 teaspoon pepper with vinegar, herbs, mustard and shallot in a small bowl. Serve drizzled over the chicken and vegetables.");
        EatTable eatTable34 = new EatTable(31, "Baked Omelet Muffins", "recipe082",
                "Step 1\n" +
                        "Preheat oven to 325 degrees F. Coat a 12-cup muffin tin with cooking spray.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Cook bacon in a large skillet over medium heat until crisp, 4 to 5 minutes. Remove with a slotted spoon to a paper towel-lined plate, leaving the bacon fat in the pan. Add broccoli and scallions and cook, stirring, until soft, about 5 minutes. Remove from heat and let cool for 5 minutes.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Meanwhile, whisk eggs, cheese, milk, salt and pepper in a large bowl. Stir in the bacon and broccoli mixture. Divide the egg mixture among the prepared muffin cups.\n" +
                        "\n" +
                        "Step 4\n" +
                        "Bake until firm to the touch, 25 to 30 minutes. Let stand for 5 minutes before removing from the muffin tin.",
                "Zucchini Noodles", "recipe083",
                "Step 1\n" +
                        "Prepare Quick Turkey Meat Sauce as directed.\n" +
                        "\n" +
                        "Step 2\n" +
                        "As the sauce cooks, divide zucchini noodles among 4 single-serving containers with lids (about 2 cups per container).\n" +
                        "\n" +
                        "Step 3\n" +
                        "Add 3/4 cup of the sauce and 2 tablespoons Parmesan to each container. Seal and refrigerate for up to 4 days.\n" +
                        "\n" +
                        "Step 4\n" +
                        "To reheat, vent the lid and microwave on High until the sauce is steaming and the noodles are tender, 2 1/2 to 3 minutes.",
                "Vegetarian Nicoise Salad", "recipe084",
                "Step 1\n" +
                        "Toss salad greens with 1 Tbsp. plus 1 tsp. vinaigrette and place on a 9-inch plate.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Gently toss green beans and potatoes with the remaining 2 tsp. vinaigrette; arrange on top of the salad greens. Top with tomatoes, egg, olives, and feta and serve.");
        EatTable eatTable35 = new EatTable(32, "Steel-Cut Oats", "recipe085",
                "Step 1\n" +
                        "Combine water (or milk) and salt in a small saucepan. Bring to a boil. Stir in oats and reduce heat to low; cook, stirring occasionally, until the oats are the desired texture, 20 to 30 minutes.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Serve with your favorite toppings, such as milk, sweetener, cinnamon, dried fruits or nuts.",
                "Zucchini Noodles", "recipe086",
                "Step 1\n" +
                        "Prepare Quick Turkey Meat Sauce as directed.\n" +
                        "\n" +
                        "Step 2\n" +
                        "As the sauce cooks, divide zucchini noodles among 4 single-serving containers with lids (about 2 cups per container).\n" +
                        "\n" +
                        "Step 3\n" +
                        "Add 3/4 cup of the sauce and 2 tablespoons Parmesan to each container. Seal and refrigerate for up to 4 days.\n" +
                        "\n" +
                        "Step 4\n" +
                        "To reheat, vent the lid and microwave on High until the sauce is steaming and the noodles are tender, 2 1/2 to 3 minutes.",
                "Polenta Bowls", "recipe087",
                "Step 1\n" +
                        "Preheat oven to 425 degrees F. Line a large rimmed baking sheet with foil. Place shallots on the prepared pan; drizzle with 1 tablespoon oil and toss to coat. Roast until lightly browned, about 12 minutes.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Add asparagus, mushrooms, vinegar, thyme, pepper, 1/4 teaspoon salt and 1 tablespoon oil to the pan with the shallots. Stir to coat. Roast until the vegetables are just tender, about 8 minutes.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Meanwhile, combine milk and stock in a large saucepan; bring to a boil over medium-high heat. Whisk in polenta; reduce heat to medium-low, and cook, whisking often, until thickened, 4 to 5 minutes. Remove from heat; stir in Parmesan.\n" +
                        "\n" +
                        "Step 4\n" +
                        "Heat the remaining 1 tablespoon oil in a large nonstick skillet over medium-high heat. Add eggs, 1 at a time; cook, using a rubber spatula to keep the eggs separate, until the whites are completely cooked but the yolks are still slightly runny, 2 to 3 minutes.\n" +
                        "\n" +
                        "Step 5\n" +
                        "Spoon the polenta into 4 shallow bowls; top with the vegetables and eggs and sprinkle with the remaining 1/4 teaspoon salt.");
        EatTableDao eatTableDao = new EatTableDao(Register.this);
        eatTableDao.add(eatTable1);
        eatTableDao.add(eatTable2);
        eatTableDao.add(eatTable3);
        eatTableDao.add(eatTable4);
        eatTableDao.add(eatTable5);
        eatTableDao.add(eatTable6);
        eatTableDao.add(eatTable7);
        eatTableDao.add(eatTable8);
        eatTableDao.add(eatTable9);
        eatTableDao.add(eatTable10);
        eatTableDao.add(eatTable11);
        eatTableDao.add(eatTable12);
        eatTableDao.add(eatTable13);
        eatTableDao.add(eatTable14);
        eatTableDao.add(eatTable15);
        eatTableDao.add(eatTable16);
        eatTableDao.add(eatTable17);
        eatTableDao.add(eatTable18);
        eatTableDao.add(eatTable19);
        eatTableDao.add(eatTable20);
        eatTableDao.add(eatTable21);
        eatTableDao.add(eatTable22);
        eatTableDao.add(eatTable23);
        eatTableDao.add(eatTable24);
        eatTableDao.add(eatTable25);
        eatTableDao.add(eatTable26);
        eatTableDao.add(eatTable27);
        eatTableDao.add(eatTable28);
        eatTableDao.add(eatTable29);
        eatTableDao.add(eatTable30);
        eatTableDao.add(eatTable31);
        eatTableDao.add(eatTable32);
        eatTableDao.add(eatTable33);
        eatTableDao.add(eatTable34);
        eatTableDao.add(eatTable35);
        ExerciseTable exerciseTable1 = new ExerciseTable(1, "Couch to 5K – run", "exercise004",
                "you will begin with a brisk 5-minute walk. After this, you will alternate 1 minute of running and 1-and-a-half minutes of walking, for a total of 20 minutes.",
                "Yoga", "exercise007",
                "A short yoga for 10 minutes",
                "HIIT", "exercise006",
                "Start by standing straight, with your feet slightly wider than shoulder-width apart, and your arms at your sides.\n" +
                        "Brace your core and, keeping your chest and chin up, push your hips back and bend your knees as if you’re going to sit in a chair.\n" +
                        "Ensuring your knees don’t bow inward or outward, drop down until your thighs are parallel to the ground, bringing your arms out in front of you in a comfortable position. Pause for one second, then extend your legs and return to the starting position.\n" +
                        "Complete 3 sets of 20 reps.");
        ExerciseTable exerciseTable2 = new ExerciseTable(2, "Jogging", "exercise004",
                "you will begin with a brisk 5-minute walk. After this, you will alternate 1 minute of running and 1-and-a-half minutes of walking, for a total of 20 minutes.",
                "HIIT", "exercise006",
                "Do a short HIIT for 10 minutes.",
                "HIIT", "exercise006",
                "Do a short HIIT for 10 minutes.");
        ExerciseTable exerciseTable3 = new ExerciseTable(3, "Yoga", "exercise007",
                "Strength Train",
                "Tennis", "exercise010",
                "Play tennis for about 30 minutes.",
                "Relax", "exercise001",
                "Strength Train");
        ExerciseTable exerciseTable4 = new ExerciseTable(4, "Jogging", "exercise004",
                "You will jogging for 30 minutes.",
                "Yoga", "exercise007",
                "A short yoga for 10 minutes",
                "Yoga", "exercise007",
                "A short yoga for 10 minutes");
        ExerciseTable exerciseTable5 = new ExerciseTable(5, "HIIT", "recipe006",
                "Do a short HIIT for 10 minutes.",
                "HIIT", "recipe006",
                "Do a short HIIT for 10 minutes.",
                "HIIT", "recipe006",
                "Do a short HIIT for 10 minutes.");
        ExerciseTable exerciseTable6 = new ExerciseTable(6, "Jogging", "recipe001",
                "ExerciseTable1Name1", "ExerciseTable1Name2", "recipe001",
                "ExerciseTable1Name2", "ExerciseTable1Name3", "recipe001",
                "ExerciseTable1Name3");
        ExerciseTable exerciseTable7 = new ExerciseTable(7, "ExerciseTable2Name1", "recipe002",
                "ExerciseTable2Name1", "ExerciseTable2Name2", "recipe002",
                "ExerciseTable2Name2", "ExerciseTable2Name3", "recipe002",
                "EatTable2Name3");
        ExerciseTable exerciseTable8 = new ExerciseTable(8, "Yoga", "recipe003",
                "ExerciseTable3Name1", "ExerciseTable3Name2", "recipe003",
                "ExerciseTable3Name2", "ExerciseTable3Name3", "recipe003",
                "ExerciseTable3Name3");
        ExerciseTable exerciseTable9 = new ExerciseTable(9, "ExerciseTable4Name1", "recipe004",
                "ExerciseTable4Name1", "ExerciseTable4Name2", "recipe004",
                "ExerciseTable4Name2", "ExerciseTable4Name3", "recipe004",
                "ExerciseTable4Name3");
        ExerciseTable exerciseTable10 = new ExerciseTable(10, "ExerciseTable5Name1", "recipe005",
                "ExerciseTable5Name1", "ExerciseTable5Name2", "recipe005",
                "ExerciseTable5Name2", "ExerciseTable5Name3", "recipe005",
                "ExerciseTable5Name3");
        ExerciseTable exerciseTable11 = new ExerciseTable(11, "ExerciseTable1Name1", "recipe001",
                "ExerciseTable1Name1", "ExerciseTable1Name2", "recipe001",
                "ExerciseTable1Name2", "ExerciseTable1Name3", "recipe001",
                "ExerciseTable1Name3");
        ExerciseTable exerciseTable12 = new ExerciseTable(12, "ExerciseTable2Name1", "recipe002",
                "ExerciseTable2Name1", "ExerciseTable2Name2", "recipe002",
                "ExerciseTable2Name2", "ExerciseTable2Name3", "recipe002",
                "EatTable2Name3");
        ExerciseTable exerciseTable13 = new ExerciseTable(13, "ExerciseTable3Name1", "recipe003",
                "ExerciseTable3Name1", "ExerciseTable3Name2", "recipe003",
                "ExerciseTable3Name2", "ExerciseTable3Name3", "recipe003",
                "ExerciseTable3Name3");
        ExerciseTable exerciseTable14 = new ExerciseTable(14, "ExerciseTable4Name1", "recipe004",
                "ExerciseTable4Name1", "ExerciseTable4Name2", "recipe004",
                "ExerciseTable4Name2", "ExerciseTable4Name3", "recipe004",
                "ExerciseTable4Name3");
        ExerciseTable exerciseTable15 = new ExerciseTable(15, "ExerciseTable5Name1", "recipe005",
                "ExerciseTable5Name1", "ExerciseTable5Name2", "recipe005",
                "ExerciseTable5Name2", "ExerciseTable5Name3", "recipe005",
                "ExerciseTable5Name3");
        ExerciseTable exerciseTable16 = new ExerciseTable(16, "ExerciseTable1Name1", "recipe001",
                "ExerciseTable1Name1", "ExerciseTable1Name2", "recipe001",
                "ExerciseTable1Name2", "ExerciseTable1Name3", "recipe001",
                "ExerciseTable1Name3");
        ExerciseTable exerciseTable17 = new ExerciseTable(17, "ExerciseTable2Name1", "recipe002",
                "ExerciseTable2Name1", "ExerciseTable2Name2", "recipe002",
                "ExerciseTable2Name2", "ExerciseTable2Name3", "recipe002",
                "EatTable2Name3");
        ExerciseTable exerciseTable18 = new ExerciseTable(18, "ExerciseTable3Name1", "recipe003",
                "ExerciseTable3Name1", "ExerciseTable3Name2", "recipe003",
                "ExerciseTable3Name2", "ExerciseTable3Name3", "recipe003",
                "ExerciseTable3Name3");
        ExerciseTable exerciseTable19 = new ExerciseTable(19, "ExerciseTable4Name1", "recipe004",
                "ExerciseTable4Name1", "ExerciseTable4Name2", "recipe004",
                "ExerciseTable4Name2", "ExerciseTable4Name3", "recipe004",
                "ExerciseTable4Name3");
        ExerciseTable exerciseTable20 = new ExerciseTable(20, "ExerciseTable5Name1", "recipe005",
                "ExerciseTable5Name1", "ExerciseTable5Name2", "recipe005",
                "ExerciseTable5Name2", "ExerciseTable5Name3", "recipe005",
                "ExerciseTable5Name3");
        ExerciseTable exerciseTable21 = new ExerciseTable(21, "ExerciseTable1Name1", "recipe001",
                "ExerciseTable1Name1", "ExerciseTable1Name2", "recipe001",
                "ExerciseTable1Name2", "ExerciseTable1Name3", "recipe001",
                "ExerciseTable1Name3");
        ExerciseTable exerciseTable22 = new ExerciseTable(22, "ExerciseTable2Name1", "recipe002",
                "ExerciseTable2Name1", "ExerciseTable2Name2", "recipe002",
                "ExerciseTable2Name2", "ExerciseTable2Name3", "recipe002",
                "EatTable2Name3");
        ExerciseTable exerciseTable23 = new ExerciseTable(23, "ExerciseTable3Name1", "recipe003",
                "ExerciseTable3Name1", "ExerciseTable3Name2", "recipe003",
                "ExerciseTable3Name2", "ExerciseTable3Name3", "recipe003",
                "ExerciseTable3Name3");
        ExerciseTable exerciseTable24 = new ExerciseTable(24, "ExerciseTable4Name1", "recipe004",
                "ExerciseTable4Name1", "ExerciseTable4Name2", "recipe004",
                "ExerciseTable4Name2", "ExerciseTable4Name3", "recipe004",
                "ExerciseTable4Name3");
        ExerciseTable exerciseTable25 = new ExerciseTable(25, "ExerciseTable5Name1", "recipe005",
                "ExerciseTable5Name1", "ExerciseTable5Name2", "recipe005",
                "ExerciseTable5Name2", "ExerciseTable5Name3", "recipe005",
                "ExerciseTable5Name3");
        ExerciseTable exerciseTable26 = new ExerciseTable(26, "ExerciseTable1Name1", "recipe001",
                "ExerciseTable1Name1", "ExerciseTable1Name2", "recipe001",
                "ExerciseTable1Name2", "ExerciseTable1Name3", "recipe001",
                "ExerciseTable1Name3");
        ExerciseTable exerciseTable27 = new ExerciseTable(27, "ExerciseTable2Name1", "recipe002",
                "ExerciseTable2Name1", "ExerciseTable2Name2", "recipe002",
                "ExerciseTable2Name2", "ExerciseTable2Name3", "recipe002",
                "EatTable2Name3");
        ExerciseTable exerciseTable28 = new ExerciseTable(28, "ExerciseTable3Name1", "recipe003",
                "ExerciseTable3Name1", "ExerciseTable3Name2", "recipe003",
                "ExerciseTable3Name2", "ExerciseTable3Name3", "recipe003",
                "ExerciseTable3Name3");
        ExerciseTable exerciseTable29 = new ExerciseTable(29, "ExerciseTable4Name1", "recipe004",
                "ExerciseTable4Name1", "ExerciseTable4Name2", "recipe004",
                "ExerciseTable4Name2", "ExerciseTable4Name3", "recipe004",
                "ExerciseTable4Name3");
        ExerciseTable exerciseTable30 = new ExerciseTable(30, "ExerciseTable5Name1", "recipe005",
                "ExerciseTable5Name1", "ExerciseTable5Name2", "recipe005",
                "ExerciseTable5Name2", "ExerciseTable5Name3", "recipe005",
                "ExerciseTable5Name3");
        ExerciseTable exerciseTable31 = new ExerciseTable(31, "ExerciseTable1Name1", "recipe001",
                "ExerciseTable1Name1", "ExerciseTable1Name2", "recipe001",
                "ExerciseTable1Name2", "ExerciseTable1Name3", "recipe001",
                "ExerciseTable1Name3");
        ExerciseTable exerciseTable32 = new ExerciseTable(32, "ExerciseTable2Name1", "recipe002",
                "ExerciseTable2Name1", "ExerciseTable2Name2", "recipe002",
                "ExerciseTable2Name2", "ExerciseTable2Name3", "recipe002",
                "EatTable2Name3");
        ExerciseTable exerciseTable33 = new ExerciseTable(33, "ExerciseTable3Name1", "recipe003",
                "ExerciseTable3Name1", "ExerciseTable3Name2", "recipe003",
                "ExerciseTable3Name2", "ExerciseTable3Name3", "recipe003",
                "ExerciseTable3Name3");
        ExerciseTable exerciseTable34 = new ExerciseTable(34, "ExerciseTable4Name1", "recipe004",
                "ExerciseTable4Name1", "ExerciseTable4Name2", "recipe004",
                "ExerciseTable4Name2", "ExerciseTable4Name3", "recipe004",
                "ExerciseTable4Name3");
        ExerciseTable exerciseTable35 = new ExerciseTable(35, "ExerciseTable5Name1", "recipe005",
                "ExerciseTable5Name1", "ExerciseTable5Name2", "recipe005",
                "ExerciseTable5Name2", "ExerciseTable5Name3", "recipe005",
                "ExerciseTable5Name3");
        ExerciseTableDao exerciseTableDao = new ExerciseTableDao(Register.this);
        exerciseTableDao.add(exerciseTable1);
        exerciseTableDao.add(exerciseTable2);
        exerciseTableDao.add(exerciseTable3);
        exerciseTableDao.add(exerciseTable4);
        exerciseTableDao.add(exerciseTable5);
        exerciseTableDao.add(exerciseTable6);
        exerciseTableDao.add(exerciseTable7);
        exerciseTableDao.add(exerciseTable8);
        exerciseTableDao.add(exerciseTable9);
        exerciseTableDao.add(exerciseTable10);
        exerciseTableDao.add(exerciseTable11);
        exerciseTableDao.add(exerciseTable12);
        exerciseTableDao.add(exerciseTable13);
        exerciseTableDao.add(exerciseTable14);
        exerciseTableDao.add(exerciseTable15);
        exerciseTableDao.add(exerciseTable16);
        exerciseTableDao.add(exerciseTable17);
        exerciseTableDao.add(exerciseTable18);
        exerciseTableDao.add(exerciseTable19);
        exerciseTableDao.add(exerciseTable20);
        exerciseTableDao.add(exerciseTable21);
        exerciseTableDao.add(exerciseTable22);
        exerciseTableDao.add(exerciseTable23);
        exerciseTableDao.add(exerciseTable24);
        exerciseTableDao.add(exerciseTable25);
        exerciseTableDao.add(exerciseTable26);
        exerciseTableDao.add(exerciseTable27);
        exerciseTableDao.add(exerciseTable28);
        exerciseTableDao.add(exerciseTable29);
        exerciseTableDao.add(exerciseTable30);
        exerciseTableDao.add(exerciseTable31);
        exerciseTableDao.add(exerciseTable32);
        exerciseTableDao.add(exerciseTable33);
        exerciseTableDao.add(exerciseTable34);
        exerciseTableDao.add(exerciseTable35);
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle("tobegood");
        toolbar.setSubtitle("Welcome! This is Register Page.");
        toolbar.setTitleTextColor(getResources().getColor(R.color.picturebrown));
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.fontblue));
    }

    private void setUserPlan(int id) {
        UserDao userDao = new UserDao(Register.this);
        User user = userDao.getUserById(id);
        /*set userplan*/
        UserPlanDao userPlanDao = new UserPlanDao(Register.this);
        if ((user.getPurpose() == 1) && (user.isVegan() == false) && (user.isEatdisorder() == false)) {
            UserPlan userPlan1 = new UserPlan(user.getId() + "1", 1, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan1);
            UserPlan userPlan2 = new UserPlan(user.getId() + "" + "2", 2, false, false, false, 2, false, false, false);
            userPlanDao.add(userPlan2);
            UserPlan userPlan3 = new UserPlan(user.getId() + "" + "3", 3, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan3);
            UserPlan userPlan4 = new UserPlan(user.getId() + "" + "4", 4, false, false, false, 2, false, false, false);
            userPlanDao.add(userPlan4);
            UserPlan userPlan5 = new UserPlan(user.getId() + "" + "5", 5, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan5);
            UserPlan userPlan6 = new UserPlan(user.getId() + "" + "6", 6, false, false, false, 2, false, false, false);
            userPlanDao.add(userPlan6);
            UserPlan userPlan7 = new UserPlan(user.getId() + "" + "7", 7, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan7);
        }
        if ((user.getPurpose() == 3) && (user.isVegan() == false) && (user.isEatdisorder() == false)) {
            UserPlan userPlan1 = new UserPlan(user.getId() + "1", 8, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan1);
            UserPlan userPlan2 = new UserPlan(user.getId() + "" + "2", 9, false, false, false, 5, false, false, false);
            userPlanDao.add(userPlan2);
            UserPlan userPlan3 = new UserPlan(user.getId() + "" + "3", 10, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan3);
            UserPlan userPlan4 = new UserPlan(user.getId() + "" + "4", 11, false, false, false, 5, false, false, false);
            userPlanDao.add(userPlan4);
            UserPlan userPlan5 = new UserPlan(user.getId() + "" + "5", 12, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan5);
            UserPlan userPlan6 = new UserPlan(user.getId() + "" + "6", 13, false, false, false, 5, false, false, false);
            userPlanDao.add(userPlan6);
            UserPlan userPlan7 = new UserPlan(user.getId() + "" + "7", 14, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan7);
        }
        if ((user.getPurpose() == 2) && (user.isVegan() == false) && (user.isEatdisorder() == false)) {
            UserPlan userPlan1 = new UserPlan(user.getId() + "1", 15, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan1);
            UserPlan userPlan2 = new UserPlan(user.getId() + "" + "2", 16, false, false, false, 2, false, false, false);
            userPlanDao.add(userPlan2);
            UserPlan userPlan3 = new UserPlan(user.getId() + "" + "3", 17, false, false, false, 3, false, false, false);
            userPlanDao.add(userPlan3);
            UserPlan userPlan4 = new UserPlan(user.getId() + "" + "4", 18, false, false, false, 4, false, false, false);
            userPlanDao.add(userPlan4);
            UserPlan userPlan5 = new UserPlan(user.getId() + "" + "5", 19, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan5);
            UserPlan userPlan6 = new UserPlan(user.getId() + "" + "6", 20, false, false, false, 2, false, false, false);
            userPlanDao.add(userPlan6);
            UserPlan userPlan7 = new UserPlan(user.getId() + "" + "7", 21, false, false, false, 3, false, false, false);
            userPlanDao.add(userPlan7);
        }
        if ((user.getPurpose() == 1) && (user.isVegan() == true)) {
            UserPlan userPlan1 = new UserPlan(user.getId() + "1", 22, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan1);
            UserPlan userPlan2 = new UserPlan(user.getId() + "" + "2", 23, false, false, false, 2, false, false, false);
            userPlanDao.add(userPlan2);
            UserPlan userPlan3 = new UserPlan(user.getId() + "" + "3", 24, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan3);
            UserPlan userPlan4 = new UserPlan(user.getId() + "" + "4", 25, false, false, false, 2, false, false, false);
            userPlanDao.add(userPlan4);
            UserPlan userPlan5 = new UserPlan(user.getId() + "" + "5", 26, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan5);
            UserPlan userPlan6 = new UserPlan(user.getId() + "" + "6", 27, false, false, false, 2, false, false, false);
            userPlanDao.add(userPlan6);
            UserPlan userPlan7 = new UserPlan(user.getId() + "" + "7", 28, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan7);
        }
        if ((user.getPurpose() == 3) && (user.isVegan() == true)) {
            UserPlan userPlan1 = new UserPlan(user.getId() + "1", 22, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan1);
            UserPlan userPlan2 = new UserPlan(user.getId() + "" + "2", 23, false, false, false, 5, false, false, false);
            userPlanDao.add(userPlan2);
            UserPlan userPlan3 = new UserPlan(user.getId() + "" + "3", 24, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan3);
            UserPlan userPlan4 = new UserPlan(user.getId() + "" + "4", 25, false, false, false, 5, false, false, false);
            userPlanDao.add(userPlan4);
            UserPlan userPlan5 = new UserPlan(user.getId() + "" + "5", 26, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan5);
            UserPlan userPlan6 = new UserPlan(user.getId() + "" + "6", 27, false, false, false, 5, false, false, false);
            userPlanDao.add(userPlan6);
            UserPlan userPlan7 = new UserPlan(user.getId() + "" + "7", 28, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan7);
        }
        if ((user.getPurpose() == 2) && (user.isVegan() == true)) {
            UserPlan userPlan1 = new UserPlan(user.getId() + "1", 22, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan1);
            UserPlan userPlan2 = new UserPlan(user.getId() + "" + "2", 23, false, false, false, 2, false, false, false);
            userPlanDao.add(userPlan2);
            UserPlan userPlan3 = new UserPlan(user.getId() + "" + "3", 24, false, false, false, 3, false, false, false);
            userPlanDao.add(userPlan3);
            UserPlan userPlan4 = new UserPlan(user.getId() + "" + "4", 25, false, false, false, 4, false, false, false);
            userPlanDao.add(userPlan4);
            UserPlan userPlan5 = new UserPlan(user.getId() + "" + "5", 26, false, false, false, 5, false, false, false);
            userPlanDao.add(userPlan5);
            UserPlan userPlan6 = new UserPlan(user.getId() + "" + "6", 27, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan6);
            UserPlan userPlan7 = new UserPlan(user.getId() + "" + "7", 28, false, false, false, 2, false, false, false);
            userPlanDao.add(userPlan7);
        }
        if ((user.getPurpose() == 1) && (user.isEatdisorder() == true) && (user.isVegan() == false)) {
            UserPlan userPlan1 = new UserPlan(user.getId() + "1", 29, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan1);
            UserPlan userPlan2 = new UserPlan(user.getId() + "" + "2", 30, false, false, false, 2, false, false, false);
            userPlanDao.add(userPlan2);
            UserPlan userPlan3 = new UserPlan(user.getId() + "" + "3", 31, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan3);
            UserPlan userPlan4 = new UserPlan(user.getId() + "" + "4", 32, false, false, false, 2, false, false, false);
            userPlanDao.add(userPlan4);
            UserPlan userPlan5 = new UserPlan(user.getId() + "" + "5", 33, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan5);
            UserPlan userPlan6 = new UserPlan(user.getId() + "" + "6", 34, false, false, false, 2, false, false, false);
            userPlanDao.add(userPlan6);
            UserPlan userPlan7 = new UserPlan(user.getId() + "" + "7", 35, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan7);
        }
        if ((user.getPurpose() == 3) && (user.isEatdisorder() == true) && (user.isVegan() == false)) {
            UserPlan userPlan1 = new UserPlan(user.getId() + "1", 29, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan1);
            UserPlan userPlan2 = new UserPlan(user.getId() + "" + "2", 30, false, false, false, 5, false, false, false);
            userPlanDao.add(userPlan2);
            UserPlan userPlan3 = new UserPlan(user.getId() + "" + "3", 31, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan3);
            UserPlan userPlan4 = new UserPlan(user.getId() + "" + "4", 32, false, false, false, 5, false, false, false);
            userPlanDao.add(userPlan4);
            UserPlan userPlan5 = new UserPlan(user.getId() + "" + "5", 33, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan5);
            UserPlan userPlan6 = new UserPlan(user.getId() + "" + "6", 34, false, false, false, 5, false, false, false);
            userPlanDao.add(userPlan6);
            UserPlan userPlan7 = new UserPlan(user.getId() + "" + "7", 35, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan7);
        }
        if ((user.getPurpose() == 2) && (user.isEatdisorder() == true) && (user.isVegan() == false)) {
            UserPlan userPlan1 = new UserPlan(user.getId() + "1", 29, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan1);
            UserPlan userPlan2 = new UserPlan(user.getId() + "" + "2", 30, false, false, false, 2, false, false, false);
            userPlanDao.add(userPlan2);
            UserPlan userPlan3 = new UserPlan(user.getId() + "" + "3", 31, false, false, false, 3, false, false, false);
            userPlanDao.add(userPlan3);
            UserPlan userPlan4 = new UserPlan(user.getId() + "" + "4", 32, false, false, false, 4, false, false, false);
            userPlanDao.add(userPlan4);
            UserPlan userPlan5 = new UserPlan(user.getId() + "" + "5", 33, false, false, false, 5, false, false, false);
            userPlanDao.add(userPlan5);
            UserPlan userPlan6 = new UserPlan(user.getId() + "" + "6", 34, false, false, false, 1, false, false, false);
            userPlanDao.add(userPlan6);
            UserPlan userPlan7 = new UserPlan(user.getId() + "" + "7", 35, false, false, false, 2, false, false, false);
            userPlanDao.add(userPlan7);
        }
    }

    public boolean findEmpty() {
        EditText edit_register_id = (EditText) findViewById(R.id.Edit_register_id);
        EditText edit_register_name = (EditText) findViewById(R.id.Edit_register_name);
        EditText edit_register_password = (EditText) findViewById(R.id.Edit_register_password);
        EditText edit_register_height = (EditText) findViewById(R.id.Edit_register_height);
        EditText edit_register_weight = (EditText) findViewById(R.id.Edit_register_weight);
        EditText editText_register_contactnumber = (EditText) findViewById(R.id.Edit_register_contactnumber);
        boolean findempty = (TextUtils.isEmpty(edit_register_id.getText()) || TextUtils.isEmpty(edit_register_name.getText()) || TextUtils.isEmpty(edit_register_password.getText()) || TextUtils.isEmpty(edit_register_height.getText()) || TextUtils.isEmpty(edit_register_weight.getText()) || TextUtils.isEmpty(editText_register_contactnumber.getText()));
        return findempty;
    }
}




