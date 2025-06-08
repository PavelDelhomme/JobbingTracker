package com.delhomme.jobbingtrack.data.local.dao.contact;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.SQLiteStatement;
import com.delhomme.jobbingtrack.data.local.entities.contact.ContactEntity;
import java.lang.Class;
import java.lang.Long;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class ContactDao_Impl implements ContactDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<ContactEntity> __insertAdapterOfContactEntity;

  private final EntityDeleteOrUpdateAdapter<ContactEntity> __updateAdapterOfContactEntity;

  public ContactDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfContactEntity = new EntityInsertAdapter<ContactEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `contacts` (`id`,`userId`,`firstName`,`lastName`,`phone`,`email`,`position`,`department`,`companyId`,`candidatureId`,`notes`,`syncHash`,`isArchived`,`isDeleted`,`createdAt`,`updatedAt`,`deletedAt`,`archivedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final ContactEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        if (entity.getUserId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getUserId());
        }
        if (entity.getFirstName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getFirstName());
        }
        if (entity.getLastName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getLastName());
        }
        if (entity.getPhone() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getPhone());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getEmail());
        }
        if (entity.getPosition() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getPosition());
        }
        if (entity.getDepartment() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getDepartment());
        }
        if (entity.getCompanyId() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getCompanyId());
        }
        if (entity.getApplicationId() == null) {
          statement.bindNull(10);
        } else {
          statement.bindText(10, entity.getApplicationId());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(11);
        } else {
          statement.bindText(11, entity.getNotes());
        }
        if (entity.getSyncHash() == null) {
          statement.bindNull(12);
        } else {
          statement.bindText(12, entity.getSyncHash());
        }
        final int _tmp = entity.isArchived() ? 1 : 0;
        statement.bindLong(13, _tmp);
        final int _tmp_1 = entity.isDeleted() ? 1 : 0;
        statement.bindLong(14, _tmp_1);
        statement.bindLong(15, entity.getCreatedAt());
        statement.bindLong(16, entity.getUpdatedAt());
        if (entity.getDeletedAt() == null) {
          statement.bindNull(17);
        } else {
          statement.bindLong(17, entity.getDeletedAt());
        }
        if (entity.getArchivedAt() == null) {
          statement.bindNull(18);
        } else {
          statement.bindLong(18, entity.getArchivedAt());
        }
      }
    };
    this.__updateAdapterOfContactEntity = new EntityDeleteOrUpdateAdapter<ContactEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `contacts` SET `id` = ?,`userId` = ?,`firstName` = ?,`lastName` = ?,`phone` = ?,`email` = ?,`position` = ?,`department` = ?,`companyId` = ?,`candidatureId` = ?,`notes` = ?,`syncHash` = ?,`isArchived` = ?,`isDeleted` = ?,`createdAt` = ?,`updatedAt` = ?,`deletedAt` = ?,`archivedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final ContactEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        if (entity.getUserId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getUserId());
        }
        if (entity.getFirstName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getFirstName());
        }
        if (entity.getLastName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getLastName());
        }
        if (entity.getPhone() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getPhone());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getEmail());
        }
        if (entity.getPosition() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getPosition());
        }
        if (entity.getDepartment() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getDepartment());
        }
        if (entity.getCompanyId() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getCompanyId());
        }
        if (entity.getApplicationId() == null) {
          statement.bindNull(10);
        } else {
          statement.bindText(10, entity.getApplicationId());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(11);
        } else {
          statement.bindText(11, entity.getNotes());
        }
        if (entity.getSyncHash() == null) {
          statement.bindNull(12);
        } else {
          statement.bindText(12, entity.getSyncHash());
        }
        final int _tmp = entity.isArchived() ? 1 : 0;
        statement.bindLong(13, _tmp);
        final int _tmp_1 = entity.isDeleted() ? 1 : 0;
        statement.bindLong(14, _tmp_1);
        statement.bindLong(15, entity.getCreatedAt());
        statement.bindLong(16, entity.getUpdatedAt());
        if (entity.getDeletedAt() == null) {
          statement.bindNull(17);
        } else {
          statement.bindLong(17, entity.getDeletedAt());
        }
        if (entity.getArchivedAt() == null) {
          statement.bindNull(18);
        } else {
          statement.bindLong(18, entity.getArchivedAt());
        }
        if (entity.getId() == null) {
          statement.bindNull(19);
        } else {
          statement.bindText(19, entity.getId());
        }
      }
    };
  }

  @Override
  public Object upsert(final ContactEntity contact, final Continuation<? super Unit> $completion) {
    if (contact == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfContactEntity.insert(_connection, contact);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object update(final ContactEntity contact, final Continuation<? super Unit> $completion) {
    if (contact == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfContactEntity.handle(_connection, contact);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Flow<List<ContactEntity>> getAllForUser(final String userId) {
    final String _sql = "SELECT * FROM contacts WHERE userId = ? ORDER BY createdAt DESC";
    return FlowUtil.createFlow(__db, false, new String[] {"contacts"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfFirstName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "firstName");
        final int _columnIndexOfLastName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastName");
        final int _columnIndexOfPhone = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "phone");
        final int _columnIndexOfEmail = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "email");
        final int _columnIndexOfPosition = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "position");
        final int _columnIndexOfDepartment = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "department");
        final int _columnIndexOfCompanyId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "companyId");
        final int _columnIndexOfCandidatureId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "candidatureId");
        final int _columnIndexOfNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notes");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final List<ContactEntity> _result = new ArrayList<ContactEntity>();
        while (_stmt.step()) {
          final ContactEntity _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
          }
          final String _tmpFirstName;
          if (_stmt.isNull(_columnIndexOfFirstName)) {
            _tmpFirstName = null;
          } else {
            _tmpFirstName = _stmt.getText(_columnIndexOfFirstName);
          }
          final String _tmpLastName;
          if (_stmt.isNull(_columnIndexOfLastName)) {
            _tmpLastName = null;
          } else {
            _tmpLastName = _stmt.getText(_columnIndexOfLastName);
          }
          final String _tmpPhone;
          if (_stmt.isNull(_columnIndexOfPhone)) {
            _tmpPhone = null;
          } else {
            _tmpPhone = _stmt.getText(_columnIndexOfPhone);
          }
          final String _tmpEmail;
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null;
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail);
          }
          final String _tmpPosition;
          if (_stmt.isNull(_columnIndexOfPosition)) {
            _tmpPosition = null;
          } else {
            _tmpPosition = _stmt.getText(_columnIndexOfPosition);
          }
          final String _tmpDepartment;
          if (_stmt.isNull(_columnIndexOfDepartment)) {
            _tmpDepartment = null;
          } else {
            _tmpDepartment = _stmt.getText(_columnIndexOfDepartment);
          }
          final String _tmpCompanyId;
          if (_stmt.isNull(_columnIndexOfCompanyId)) {
            _tmpCompanyId = null;
          } else {
            _tmpCompanyId = _stmt.getText(_columnIndexOfCompanyId);
          }
          final String _tmpCandidatureId;
          if (_stmt.isNull(_columnIndexOfCandidatureId)) {
            _tmpCandidatureId = null;
          } else {
            _tmpCandidatureId = _stmt.getText(_columnIndexOfCandidatureId);
          }
          final String _tmpNotes;
          if (_stmt.isNull(_columnIndexOfNotes)) {
            _tmpNotes = null;
          } else {
            _tmpNotes = _stmt.getText(_columnIndexOfNotes);
          }
          final String _tmpSyncHash;
          if (_stmt.isNull(_columnIndexOfSyncHash)) {
            _tmpSyncHash = null;
          } else {
            _tmpSyncHash = _stmt.getText(_columnIndexOfSyncHash);
          }
          final boolean _tmpIsArchived;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp != 0;
          final boolean _tmpIsDeleted;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsDeleted));
          _tmpIsDeleted = _tmp_1 != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final long _tmpUpdatedAt;
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt);
          final Long _tmpDeletedAt;
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null;
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt);
          }
          final Long _tmpArchivedAt;
          if (_stmt.isNull(_columnIndexOfArchivedAt)) {
            _tmpArchivedAt = null;
          } else {
            _tmpArchivedAt = _stmt.getLong(_columnIndexOfArchivedAt);
          }
          _item = new ContactEntity(_tmpId,_tmpUserId,_tmpFirstName,_tmpLastName,_tmpPhone,_tmpEmail,_tmpPosition,_tmpDepartment,_tmpCompanyId,_tmpCandidatureId,_tmpNotes,_tmpSyncHash,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<List<ContactEntity>> getAllActiveForUser(final String userId) {
    final String _sql = "\n"
            + "      SELECT * FROM contacts\n"
            + "       WHERE userId    = ?\n"
            + "         AND isDeleted = 0\n"
            + "         AND isArchived= 0\n"
            + "      ORDER BY lastName, firstName\n"
            + "    ";
    return FlowUtil.createFlow(__db, false, new String[] {"contacts"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfFirstName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "firstName");
        final int _columnIndexOfLastName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastName");
        final int _columnIndexOfPhone = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "phone");
        final int _columnIndexOfEmail = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "email");
        final int _columnIndexOfPosition = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "position");
        final int _columnIndexOfDepartment = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "department");
        final int _columnIndexOfCompanyId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "companyId");
        final int _columnIndexOfCandidatureId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "candidatureId");
        final int _columnIndexOfNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notes");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final List<ContactEntity> _result = new ArrayList<ContactEntity>();
        while (_stmt.step()) {
          final ContactEntity _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
          }
          final String _tmpFirstName;
          if (_stmt.isNull(_columnIndexOfFirstName)) {
            _tmpFirstName = null;
          } else {
            _tmpFirstName = _stmt.getText(_columnIndexOfFirstName);
          }
          final String _tmpLastName;
          if (_stmt.isNull(_columnIndexOfLastName)) {
            _tmpLastName = null;
          } else {
            _tmpLastName = _stmt.getText(_columnIndexOfLastName);
          }
          final String _tmpPhone;
          if (_stmt.isNull(_columnIndexOfPhone)) {
            _tmpPhone = null;
          } else {
            _tmpPhone = _stmt.getText(_columnIndexOfPhone);
          }
          final String _tmpEmail;
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null;
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail);
          }
          final String _tmpPosition;
          if (_stmt.isNull(_columnIndexOfPosition)) {
            _tmpPosition = null;
          } else {
            _tmpPosition = _stmt.getText(_columnIndexOfPosition);
          }
          final String _tmpDepartment;
          if (_stmt.isNull(_columnIndexOfDepartment)) {
            _tmpDepartment = null;
          } else {
            _tmpDepartment = _stmt.getText(_columnIndexOfDepartment);
          }
          final String _tmpCompanyId;
          if (_stmt.isNull(_columnIndexOfCompanyId)) {
            _tmpCompanyId = null;
          } else {
            _tmpCompanyId = _stmt.getText(_columnIndexOfCompanyId);
          }
          final String _tmpCandidatureId;
          if (_stmt.isNull(_columnIndexOfCandidatureId)) {
            _tmpCandidatureId = null;
          } else {
            _tmpCandidatureId = _stmt.getText(_columnIndexOfCandidatureId);
          }
          final String _tmpNotes;
          if (_stmt.isNull(_columnIndexOfNotes)) {
            _tmpNotes = null;
          } else {
            _tmpNotes = _stmt.getText(_columnIndexOfNotes);
          }
          final String _tmpSyncHash;
          if (_stmt.isNull(_columnIndexOfSyncHash)) {
            _tmpSyncHash = null;
          } else {
            _tmpSyncHash = _stmt.getText(_columnIndexOfSyncHash);
          }
          final boolean _tmpIsArchived;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp != 0;
          final boolean _tmpIsDeleted;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsDeleted));
          _tmpIsDeleted = _tmp_1 != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final long _tmpUpdatedAt;
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt);
          final Long _tmpDeletedAt;
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null;
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt);
          }
          final Long _tmpArchivedAt;
          if (_stmt.isNull(_columnIndexOfArchivedAt)) {
            _tmpArchivedAt = null;
          } else {
            _tmpArchivedAt = _stmt.getLong(_columnIndexOfArchivedAt);
          }
          _item = new ContactEntity(_tmpId,_tmpUserId,_tmpFirstName,_tmpLastName,_tmpPhone,_tmpEmail,_tmpPosition,_tmpDepartment,_tmpCompanyId,_tmpCandidatureId,_tmpNotes,_tmpSyncHash,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<List<ContactEntity>> getArchivedForUser(final String userId) {
    final String _sql = "\n"
            + "      SELECT * FROM contacts\n"
            + "       WHERE userId    = ?\n"
            + "         AND isArchived= 1\n"
            + "      ORDER BY lastName, firstName\n"
            + "    ";
    return FlowUtil.createFlow(__db, false, new String[] {"contacts"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfFirstName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "firstName");
        final int _columnIndexOfLastName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastName");
        final int _columnIndexOfPhone = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "phone");
        final int _columnIndexOfEmail = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "email");
        final int _columnIndexOfPosition = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "position");
        final int _columnIndexOfDepartment = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "department");
        final int _columnIndexOfCompanyId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "companyId");
        final int _columnIndexOfCandidatureId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "candidatureId");
        final int _columnIndexOfNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notes");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final List<ContactEntity> _result = new ArrayList<ContactEntity>();
        while (_stmt.step()) {
          final ContactEntity _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
          }
          final String _tmpFirstName;
          if (_stmt.isNull(_columnIndexOfFirstName)) {
            _tmpFirstName = null;
          } else {
            _tmpFirstName = _stmt.getText(_columnIndexOfFirstName);
          }
          final String _tmpLastName;
          if (_stmt.isNull(_columnIndexOfLastName)) {
            _tmpLastName = null;
          } else {
            _tmpLastName = _stmt.getText(_columnIndexOfLastName);
          }
          final String _tmpPhone;
          if (_stmt.isNull(_columnIndexOfPhone)) {
            _tmpPhone = null;
          } else {
            _tmpPhone = _stmt.getText(_columnIndexOfPhone);
          }
          final String _tmpEmail;
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null;
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail);
          }
          final String _tmpPosition;
          if (_stmt.isNull(_columnIndexOfPosition)) {
            _tmpPosition = null;
          } else {
            _tmpPosition = _stmt.getText(_columnIndexOfPosition);
          }
          final String _tmpDepartment;
          if (_stmt.isNull(_columnIndexOfDepartment)) {
            _tmpDepartment = null;
          } else {
            _tmpDepartment = _stmt.getText(_columnIndexOfDepartment);
          }
          final String _tmpCompanyId;
          if (_stmt.isNull(_columnIndexOfCompanyId)) {
            _tmpCompanyId = null;
          } else {
            _tmpCompanyId = _stmt.getText(_columnIndexOfCompanyId);
          }
          final String _tmpCandidatureId;
          if (_stmt.isNull(_columnIndexOfCandidatureId)) {
            _tmpCandidatureId = null;
          } else {
            _tmpCandidatureId = _stmt.getText(_columnIndexOfCandidatureId);
          }
          final String _tmpNotes;
          if (_stmt.isNull(_columnIndexOfNotes)) {
            _tmpNotes = null;
          } else {
            _tmpNotes = _stmt.getText(_columnIndexOfNotes);
          }
          final String _tmpSyncHash;
          if (_stmt.isNull(_columnIndexOfSyncHash)) {
            _tmpSyncHash = null;
          } else {
            _tmpSyncHash = _stmt.getText(_columnIndexOfSyncHash);
          }
          final boolean _tmpIsArchived;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp != 0;
          final boolean _tmpIsDeleted;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsDeleted));
          _tmpIsDeleted = _tmp_1 != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final long _tmpUpdatedAt;
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt);
          final Long _tmpDeletedAt;
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null;
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt);
          }
          final Long _tmpArchivedAt;
          if (_stmt.isNull(_columnIndexOfArchivedAt)) {
            _tmpArchivedAt = null;
          } else {
            _tmpArchivedAt = _stmt.getLong(_columnIndexOfArchivedAt);
          }
          _item = new ContactEntity(_tmpId,_tmpUserId,_tmpFirstName,_tmpLastName,_tmpPhone,_tmpEmail,_tmpPosition,_tmpDepartment,_tmpCompanyId,_tmpCandidatureId,_tmpNotes,_tmpSyncHash,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<List<ContactEntity>> getDeletedForUser(final String userId) {
    final String _sql = "\n"
            + "      SELECT * FROM contacts\n"
            + "       WHERE userId    = ?\n"
            + "         AND isDeleted = 1\n"
            + "      ORDER BY lastName, firstName\n"
            + "    ";
    return FlowUtil.createFlow(__db, false, new String[] {"contacts"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfFirstName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "firstName");
        final int _columnIndexOfLastName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastName");
        final int _columnIndexOfPhone = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "phone");
        final int _columnIndexOfEmail = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "email");
        final int _columnIndexOfPosition = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "position");
        final int _columnIndexOfDepartment = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "department");
        final int _columnIndexOfCompanyId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "companyId");
        final int _columnIndexOfCandidatureId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "candidatureId");
        final int _columnIndexOfNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notes");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final List<ContactEntity> _result = new ArrayList<ContactEntity>();
        while (_stmt.step()) {
          final ContactEntity _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
          }
          final String _tmpFirstName;
          if (_stmt.isNull(_columnIndexOfFirstName)) {
            _tmpFirstName = null;
          } else {
            _tmpFirstName = _stmt.getText(_columnIndexOfFirstName);
          }
          final String _tmpLastName;
          if (_stmt.isNull(_columnIndexOfLastName)) {
            _tmpLastName = null;
          } else {
            _tmpLastName = _stmt.getText(_columnIndexOfLastName);
          }
          final String _tmpPhone;
          if (_stmt.isNull(_columnIndexOfPhone)) {
            _tmpPhone = null;
          } else {
            _tmpPhone = _stmt.getText(_columnIndexOfPhone);
          }
          final String _tmpEmail;
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null;
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail);
          }
          final String _tmpPosition;
          if (_stmt.isNull(_columnIndexOfPosition)) {
            _tmpPosition = null;
          } else {
            _tmpPosition = _stmt.getText(_columnIndexOfPosition);
          }
          final String _tmpDepartment;
          if (_stmt.isNull(_columnIndexOfDepartment)) {
            _tmpDepartment = null;
          } else {
            _tmpDepartment = _stmt.getText(_columnIndexOfDepartment);
          }
          final String _tmpCompanyId;
          if (_stmt.isNull(_columnIndexOfCompanyId)) {
            _tmpCompanyId = null;
          } else {
            _tmpCompanyId = _stmt.getText(_columnIndexOfCompanyId);
          }
          final String _tmpCandidatureId;
          if (_stmt.isNull(_columnIndexOfCandidatureId)) {
            _tmpCandidatureId = null;
          } else {
            _tmpCandidatureId = _stmt.getText(_columnIndexOfCandidatureId);
          }
          final String _tmpNotes;
          if (_stmt.isNull(_columnIndexOfNotes)) {
            _tmpNotes = null;
          } else {
            _tmpNotes = _stmt.getText(_columnIndexOfNotes);
          }
          final String _tmpSyncHash;
          if (_stmt.isNull(_columnIndexOfSyncHash)) {
            _tmpSyncHash = null;
          } else {
            _tmpSyncHash = _stmt.getText(_columnIndexOfSyncHash);
          }
          final boolean _tmpIsArchived;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp != 0;
          final boolean _tmpIsDeleted;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsDeleted));
          _tmpIsDeleted = _tmp_1 != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final long _tmpUpdatedAt;
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt);
          final Long _tmpDeletedAt;
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null;
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt);
          }
          final Long _tmpArchivedAt;
          if (_stmt.isNull(_columnIndexOfArchivedAt)) {
            _tmpArchivedAt = null;
          } else {
            _tmpArchivedAt = _stmt.getLong(_columnIndexOfArchivedAt);
          }
          _item = new ContactEntity(_tmpId,_tmpUserId,_tmpFirstName,_tmpLastName,_tmpPhone,_tmpEmail,_tmpPosition,_tmpDepartment,_tmpCompanyId,_tmpCandidatureId,_tmpNotes,_tmpSyncHash,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<ContactEntity> getByIdForUser(final String id, final String userId) {
    final String _sql = "SELECT * FROM contacts WHERE id = ? AND userId = ?";
    return FlowUtil.createFlow(__db, false, new String[] {"contacts"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (id == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, id);
        }
        _argIndex = 2;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _columnIndexOfFirstName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "firstName");
        final int _columnIndexOfLastName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastName");
        final int _columnIndexOfPhone = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "phone");
        final int _columnIndexOfEmail = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "email");
        final int _columnIndexOfPosition = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "position");
        final int _columnIndexOfDepartment = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "department");
        final int _columnIndexOfCompanyId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "companyId");
        final int _columnIndexOfCandidatureId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "candidatureId");
        final int _columnIndexOfNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notes");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final ContactEntity _result;
        if (_stmt.step()) {
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpUserId;
          if (_stmt.isNull(_columnIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_columnIndexOfUserId);
          }
          final String _tmpFirstName;
          if (_stmt.isNull(_columnIndexOfFirstName)) {
            _tmpFirstName = null;
          } else {
            _tmpFirstName = _stmt.getText(_columnIndexOfFirstName);
          }
          final String _tmpLastName;
          if (_stmt.isNull(_columnIndexOfLastName)) {
            _tmpLastName = null;
          } else {
            _tmpLastName = _stmt.getText(_columnIndexOfLastName);
          }
          final String _tmpPhone;
          if (_stmt.isNull(_columnIndexOfPhone)) {
            _tmpPhone = null;
          } else {
            _tmpPhone = _stmt.getText(_columnIndexOfPhone);
          }
          final String _tmpEmail;
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null;
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail);
          }
          final String _tmpPosition;
          if (_stmt.isNull(_columnIndexOfPosition)) {
            _tmpPosition = null;
          } else {
            _tmpPosition = _stmt.getText(_columnIndexOfPosition);
          }
          final String _tmpDepartment;
          if (_stmt.isNull(_columnIndexOfDepartment)) {
            _tmpDepartment = null;
          } else {
            _tmpDepartment = _stmt.getText(_columnIndexOfDepartment);
          }
          final String _tmpCompanyId;
          if (_stmt.isNull(_columnIndexOfCompanyId)) {
            _tmpCompanyId = null;
          } else {
            _tmpCompanyId = _stmt.getText(_columnIndexOfCompanyId);
          }
          final String _tmpCandidatureId;
          if (_stmt.isNull(_columnIndexOfCandidatureId)) {
            _tmpCandidatureId = null;
          } else {
            _tmpCandidatureId = _stmt.getText(_columnIndexOfCandidatureId);
          }
          final String _tmpNotes;
          if (_stmt.isNull(_columnIndexOfNotes)) {
            _tmpNotes = null;
          } else {
            _tmpNotes = _stmt.getText(_columnIndexOfNotes);
          }
          final String _tmpSyncHash;
          if (_stmt.isNull(_columnIndexOfSyncHash)) {
            _tmpSyncHash = null;
          } else {
            _tmpSyncHash = _stmt.getText(_columnIndexOfSyncHash);
          }
          final boolean _tmpIsArchived;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp != 0;
          final boolean _tmpIsDeleted;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsDeleted));
          _tmpIsDeleted = _tmp_1 != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final long _tmpUpdatedAt;
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt);
          final Long _tmpDeletedAt;
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null;
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt);
          }
          final Long _tmpArchivedAt;
          if (_stmt.isNull(_columnIndexOfArchivedAt)) {
            _tmpArchivedAt = null;
          } else {
            _tmpArchivedAt = _stmt.getLong(_columnIndexOfArchivedAt);
          }
          _result = new ContactEntity(_tmpId,_tmpUserId,_tmpFirstName,_tmpLastName,_tmpPhone,_tmpEmail,_tmpPosition,_tmpDepartment,_tmpCompanyId,_tmpCandidatureId,_tmpNotes,_tmpSyncHash,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Object archive(final List<String> ids, final String userId,
      final Continuation<? super Unit> $completion) {
    final StringBuilder _stringBuilder = new StringBuilder();
    _stringBuilder.append("UPDATE contacts SET isArchived = 1 WHERE id IN(");
    final int _inputSize = ids.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(") AND userId = ");
    _stringBuilder.append("?");
    final String _sql = _stringBuilder.toString();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        for (String _item : ids) {
          if (_item == null) {
            _stmt.bindNull(_argIndex);
          } else {
            _stmt.bindText(_argIndex, _item);
          }
          _argIndex++;
        }
        _argIndex = 1 + _inputSize;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object softDelete(final List<String> ids, final String userId,
      final Continuation<? super Unit> $completion) {
    final StringBuilder _stringBuilder = new StringBuilder();
    _stringBuilder.append("UPDATE contacts SET isDeleted  = 1 WHERE id IN(");
    final int _inputSize = ids.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(") AND userId = ");
    _stringBuilder.append("?");
    final String _sql = _stringBuilder.toString();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        for (String _item : ids) {
          if (_item == null) {
            _stmt.bindNull(_argIndex);
          } else {
            _stmt.bindText(_argIndex, _item);
          }
          _argIndex++;
        }
        _argIndex = 1 + _inputSize;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object restore(final List<String> ids, final String userId,
      final Continuation<? super Unit> $completion) {
    final StringBuilder _stringBuilder = new StringBuilder();
    _stringBuilder.append("UPDATE contacts SET isDeleted  = 0 WHERE id IN(");
    final int _inputSize = ids.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(") AND userId = ");
    _stringBuilder.append("?");
    final String _sql = _stringBuilder.toString();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        for (String _item : ids) {
          if (_item == null) {
            _stmt.bindNull(_argIndex);
          } else {
            _stmt.bindText(_argIndex, _item);
          }
          _argIndex++;
        }
        _argIndex = 1 + _inputSize;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object deleteForever(final List<String> ids, final String userId,
      final Continuation<? super Unit> $completion) {
    final StringBuilder _stringBuilder = new StringBuilder();
    _stringBuilder.append("DELETE FROM contacts WHERE id IN(");
    final int _inputSize = ids.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(") AND userId = ");
    _stringBuilder.append("?");
    final String _sql = _stringBuilder.toString();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        for (String _item : ids) {
          if (_item == null) {
            _stmt.bindNull(_argIndex);
          } else {
            _stmt.bindText(_argIndex, _item);
          }
          _argIndex++;
        }
        _argIndex = 1 + _inputSize;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object deleteAllForUser(final String userId,
      final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM contacts WHERE userId = ?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
