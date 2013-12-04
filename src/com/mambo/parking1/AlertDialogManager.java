package com.mambo.parking1;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class AlertDialogManager
{
  public void showAlertDialog(Context paramContext, String paramString1, String paramString2, Boolean paramBoolean)
  {
    AlertDialog localAlertDialog = new AlertDialog.Builder(paramContext).create();
    localAlertDialog.setTitle(paramString1);
    localAlertDialog.setMessage(paramString2);
    if (paramBoolean != null)
      if (!paramBoolean.booleanValue())
        break label70;
    label70: for (int i = 2130837548; ; i = 2130837538)
    {
      localAlertDialog.setIcon(i);
      localAlertDialog.setButton("OK", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
        }
      });
      localAlertDialog.show();
      return;
    }
  }
}

