/*
 * BasicsTest.cpp
 *
 *  Created on: Oct 1, 2016
 *      Author: popeye
 */

#include "gtest/gtest.h"
#include "Basics.h"
namespace {
  class BasicsTest : public ::testing::Test {
  protected:
    Basics basics;
  };

  TEST_F(BasicsTest, TruthStatusReturnsTrueWhenGivenTrue)
  {
	  ASSERT_TRUE(basics.TruthStatus(true));
  }

  TEST_F(BasicsTest, TruthStatusReturnsFalseWhenGivenFalse)
  {
	  ASSERT_FALSE(basics.TruthStatus(false));
  }

  TEST_F(BasicsTest, TruthStatusReturnsTrueFor_AND_WithTrueArguments)
  {
	  bool a = true;
	  bool b = true;

	  ASSERT_TRUE(basics.TruthStatus(a && b));
  }

  TEST_F(BasicsTest, TruthStatusReturnsFalseFor_AND_WithNonTrueArguemnts)
  {
	  bool a = true;
	  bool b = false;

	  ASSERT_FALSE(basics.TruthStatus(a && b));
  }


}


