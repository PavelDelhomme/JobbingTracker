package com.delhomme.jobbingtrack.data.local.dao.followups;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomRawQuery;
import androidx.room.RoomSQLiteQuery;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.SQLiteStatement;
import androidx.sqlite.db.SimpleSQLiteQuery;
import com.delhomme.jobbingtrack.data.local.entities.followup.FollowUpEntity;
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
public final class FollowUpDao_Impl implements FollowUpDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<FollowUpEntity> __insertAdapterOfRelanceEntity;

  private final EntityDeleteOrUpdateAdapter<FollowUpEntity> __updateAdapterOfRelanceEntity;

  public FollowUpDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfRelanceEntity = new EntityInsertAdapter<FollowUpEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `relances` (`id`,`userId`,`date`,`type`,`responseStatus`,`notes`,`candidatureId`,`companyId`,`contactId`,`syncHash`,`isArchived`,`isDeleted`,`createdAt`,`updatedAt`,`deletedAt`,`archivedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final FollowUpEntity entity) {
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
        statement.bindLong(3, entity.getDate());
        if (entity.getType() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getType());
        }
        if (entity.getResponseStatus() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getResponseStatus());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getNotes());
        }
        if (entity.getApplicationId() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getApplicationId());
        }
        if (entity.getCompanyId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getCompanyId());
        }
        if (entity.getContactId() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getContactId());
        }
        if (entity.getSyncHash() == null) {
          statement.bindNull(10);
        } else {
          statement.bindText(10, entity.getSyncHash());
        }
        final int _tmp = entity.isArchived() ? 1 : 0;
        statement.bindLong(11, _tmp);
        final int _tmp_1 = entity.isDeleted() ? 1 : 0;
        statement.bindLong(12, _tmp_1);
        statement.bindLong(13, entity.getCreatedAt());
        statement.bindLong(14, entity.getUpdatedAt());
        if (entity.getDeletedAt() == null) {
          statement.bindNull(15);
        } else {
          statement.bindLong(15, entity.getDeletedAt());
        }
        if (entity.getArchivedAt() == null) {
          statement.bindNull(16);
        } else {
          statement.bindLong(16, entity.getArchivedAt());
        }
      }
    };
    this.__updateAdapterOfRelanceEntity = new EntityDeleteOrUpdateAdapter<FollowUpEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `relances` SET `id` = ?,`userId` = ?,`date` = ?,`type` = ?,`responseStatus` = ?,`notes` = ?,`candidatureId` = ?,`companyId` = ?,`contactId` = ?,`syncHash` = ?,`isArchived` = ?,`isDeleted` = ?,`createdAt` = ?,`updatedAt` = ?,`deletedAt` = ?,`archivedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final FollowUpEntity entity) {
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
        statement.bindLong(3, entity.getDate());
        if (entity.getType() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getType());
        }
        if (entity.getResponseStatus() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getResponseStatus());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getNotes());
        }
        if (entity.getApplicationId() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getApplicationId());
        }
        if (entity.getCompanyId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getCompanyId());
        }
        if (entity.getContactId() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getContactId());
        }
        if (entity.getSyncHash() == null) {
          statement.bindNull(10);
        } else {
          statement.bindText(10, entity.getSyncHash());
        }
        final int _tmp = entity.isArchived() ? 1 : 0;
        statement.bindLong(11, _tmp);
        final int _tmp_1 = entity.isDeleted() ? 1 : 0;
        statement.bindLong(12, _tmp_1);
        statement.bindLong(13, entity.getCreatedAt());
        statement.bindLong(14, entity.getUpdatedAt());
        if (entity.getDeletedAt() == null) {
          statement.bindNull(15);
        } else {
          statement.bindLong(15, entity.getDeletedAt());
        }
        if (entity.getArchivedAt() == null) {
          statement.bindNull(16);
        } else {
          statement.bindLong(16, entity.getArchivedAt());
        }
        if (entity.getId() == null) {
          statement.bindNull(17);
        } else {
          statement.bindText(17, entity.getId());
        }
      }
    };
  }

  @Override
  public Object upsert(final FollowUpEntity relance, final Continuation<? super Unit> $completion) {
    if (relance == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfRelanceEntity.insert(_connection, relance);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object update(final FollowUpEntity relance, final Continuation<? super Unit> $completion) {
    if (relance == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfRelanceEntity.handle(_connection, relance);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Flow<List<FollowUpEntity>> getAllForUser(final String userId) {
    final String _sql = "SELECT * FROM relances WHERE userId = ? ORDER BY date DESC";
    return FlowUtil.createFlow(__db, false, new String[] {"relances"}, (_connection) -> {
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
        final int _columnIndexOfDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "date");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final int _columnIndexOfResponseStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "responseStatus");
        final int _columnIndexOfNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notes");
        final int _columnIndexOfCandidatureId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "candidatureId");
        final int _columnIndexOfCompanyId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "companyId");
        final int _columnIndexOfContactId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "contactId");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final List<FollowUpEntity> _result = new ArrayList<FollowUpEntity>();
        while (_stmt.step()) {
          final FollowUpEntity _item;
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
          final long _tmpDate;
          _tmpDate = _stmt.getLong(_columnIndexOfDate);
          final String _tmpType;
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmpType = null;
          } else {
            _tmpType = _stmt.getText(_columnIndexOfType);
          }
          final String _tmpResponseStatus;
          if (_stmt.isNull(_columnIndexOfResponseStatus)) {
            _tmpResponseStatus = null;
          } else {
            _tmpResponseStatus = _stmt.getText(_columnIndexOfResponseStatus);
          }
          final String _tmpNotes;
          if (_stmt.isNull(_columnIndexOfNotes)) {
            _tmpNotes = null;
          } else {
            _tmpNotes = _stmt.getText(_columnIndexOfNotes);
          }
          final String _tmpCandidatureId;
          if (_stmt.isNull(_columnIndexOfCandidatureId)) {
            _tmpCandidatureId = null;
          } else {
            _tmpCandidatureId = _stmt.getText(_columnIndexOfCandidatureId);
          }
          final String _tmpCompanyId;
          if (_stmt.isNull(_columnIndexOfCompanyId)) {
            _tmpCompanyId = null;
          } else {
            _tmpCompanyId = _stmt.getText(_columnIndexOfCompanyId);
          }
          final String _tmpContactId;
          if (_stmt.isNull(_columnIndexOfContactId)) {
            _tmpContactId = null;
          } else {
            _tmpContactId = _stmt.getText(_columnIndexOfContactId);
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
          _item = new FollowUpEntity(_tmpId,_tmpUserId,_tmpDate,_tmpType,_tmpResponseStatus,_tmpNotes,_tmpCandidatureId,_tmpCompanyId,_tmpContactId,_tmpSyncHash,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<List<FollowUpEntity>> getAllActiveForUser(final String userId) {
    final String _sql = "\n"
            + "      SELECT * FROM relances\n"
            + "       WHERE userId=? AND isDeleted=0 AND isArchived=0\n"
            + "       ORDER BY date DESC\n"
            + "    ";
    return FlowUtil.createFlow(__db, false, new String[] {"relances"}, (_connection) -> {
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
        final int _columnIndexOfDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "date");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final int _columnIndexOfResponseStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "responseStatus");
        final int _columnIndexOfNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notes");
        final int _columnIndexOfCandidatureId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "candidatureId");
        final int _columnIndexOfCompanyId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "companyId");
        final int _columnIndexOfContactId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "contactId");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final List<FollowUpEntity> _result = new ArrayList<FollowUpEntity>();
        while (_stmt.step()) {
          final FollowUpEntity _item;
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
          final long _tmpDate;
          _tmpDate = _stmt.getLong(_columnIndexOfDate);
          final String _tmpType;
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmpType = null;
          } else {
            _tmpType = _stmt.getText(_columnIndexOfType);
          }
          final String _tmpResponseStatus;
          if (_stmt.isNull(_columnIndexOfResponseStatus)) {
            _tmpResponseStatus = null;
          } else {
            _tmpResponseStatus = _stmt.getText(_columnIndexOfResponseStatus);
          }
          final String _tmpNotes;
          if (_stmt.isNull(_columnIndexOfNotes)) {
            _tmpNotes = null;
          } else {
            _tmpNotes = _stmt.getText(_columnIndexOfNotes);
          }
          final String _tmpCandidatureId;
          if (_stmt.isNull(_columnIndexOfCandidatureId)) {
            _tmpCandidatureId = null;
          } else {
            _tmpCandidatureId = _stmt.getText(_columnIndexOfCandidatureId);
          }
          final String _tmpCompanyId;
          if (_stmt.isNull(_columnIndexOfCompanyId)) {
            _tmpCompanyId = null;
          } else {
            _tmpCompanyId = _stmt.getText(_columnIndexOfCompanyId);
          }
          final String _tmpContactId;
          if (_stmt.isNull(_columnIndexOfContactId)) {
            _tmpContactId = null;
          } else {
            _tmpContactId = _stmt.getText(_columnIndexOfContactId);
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
          _item = new FollowUpEntity(_tmpId,_tmpUserId,_tmpDate,_tmpType,_tmpResponseStatus,_tmpNotes,_tmpCandidatureId,_tmpCompanyId,_tmpContactId,_tmpSyncHash,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<List<FollowUpEntity>> getArchivedForUser(final String userId) {
    final String _sql = "\n"
            + "      SELECT * FROM relances\n"
            + "       WHERE userId=? AND isArchived=1 AND isDeleted=0\n"
            + "       ORDER BY date DESC\n"
            + "    ";
    return FlowUtil.createFlow(__db, false, new String[] {"relances"}, (_connection) -> {
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
        final int _columnIndexOfDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "date");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final int _columnIndexOfResponseStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "responseStatus");
        final int _columnIndexOfNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notes");
        final int _columnIndexOfCandidatureId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "candidatureId");
        final int _columnIndexOfCompanyId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "companyId");
        final int _columnIndexOfContactId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "contactId");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final List<FollowUpEntity> _result = new ArrayList<FollowUpEntity>();
        while (_stmt.step()) {
          final FollowUpEntity _item;
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
          final long _tmpDate;
          _tmpDate = _stmt.getLong(_columnIndexOfDate);
          final String _tmpType;
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmpType = null;
          } else {
            _tmpType = _stmt.getText(_columnIndexOfType);
          }
          final String _tmpResponseStatus;
          if (_stmt.isNull(_columnIndexOfResponseStatus)) {
            _tmpResponseStatus = null;
          } else {
            _tmpResponseStatus = _stmt.getText(_columnIndexOfResponseStatus);
          }
          final String _tmpNotes;
          if (_stmt.isNull(_columnIndexOfNotes)) {
            _tmpNotes = null;
          } else {
            _tmpNotes = _stmt.getText(_columnIndexOfNotes);
          }
          final String _tmpCandidatureId;
          if (_stmt.isNull(_columnIndexOfCandidatureId)) {
            _tmpCandidatureId = null;
          } else {
            _tmpCandidatureId = _stmt.getText(_columnIndexOfCandidatureId);
          }
          final String _tmpCompanyId;
          if (_stmt.isNull(_columnIndexOfCompanyId)) {
            _tmpCompanyId = null;
          } else {
            _tmpCompanyId = _stmt.getText(_columnIndexOfCompanyId);
          }
          final String _tmpContactId;
          if (_stmt.isNull(_columnIndexOfContactId)) {
            _tmpContactId = null;
          } else {
            _tmpContactId = _stmt.getText(_columnIndexOfContactId);
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
          _item = new FollowUpEntity(_tmpId,_tmpUserId,_tmpDate,_tmpType,_tmpResponseStatus,_tmpNotes,_tmpCandidatureId,_tmpCompanyId,_tmpContactId,_tmpSyncHash,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<List<FollowUpEntity>> getDeletedForUser(final String userId) {
    final String _sql = "\n"
            + "      SELECT * FROM relances\n"
            + "       WHERE userId=? AND isDeleted=1\n"
            + "       ORDER BY date DESC\n"
            + "    ";
    return FlowUtil.createFlow(__db, false, new String[] {"relances"}, (_connection) -> {
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
        final int _columnIndexOfDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "date");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final int _columnIndexOfResponseStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "responseStatus");
        final int _columnIndexOfNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notes");
        final int _columnIndexOfCandidatureId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "candidatureId");
        final int _columnIndexOfCompanyId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "companyId");
        final int _columnIndexOfContactId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "contactId");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final List<FollowUpEntity> _result = new ArrayList<FollowUpEntity>();
        while (_stmt.step()) {
          final FollowUpEntity _item;
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
          final long _tmpDate;
          _tmpDate = _stmt.getLong(_columnIndexOfDate);
          final String _tmpType;
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmpType = null;
          } else {
            _tmpType = _stmt.getText(_columnIndexOfType);
          }
          final String _tmpResponseStatus;
          if (_stmt.isNull(_columnIndexOfResponseStatus)) {
            _tmpResponseStatus = null;
          } else {
            _tmpResponseStatus = _stmt.getText(_columnIndexOfResponseStatus);
          }
          final String _tmpNotes;
          if (_stmt.isNull(_columnIndexOfNotes)) {
            _tmpNotes = null;
          } else {
            _tmpNotes = _stmt.getText(_columnIndexOfNotes);
          }
          final String _tmpCandidatureId;
          if (_stmt.isNull(_columnIndexOfCandidatureId)) {
            _tmpCandidatureId = null;
          } else {
            _tmpCandidatureId = _stmt.getText(_columnIndexOfCandidatureId);
          }
          final String _tmpCompanyId;
          if (_stmt.isNull(_columnIndexOfCompanyId)) {
            _tmpCompanyId = null;
          } else {
            _tmpCompanyId = _stmt.getText(_columnIndexOfCompanyId);
          }
          final String _tmpContactId;
          if (_stmt.isNull(_columnIndexOfContactId)) {
            _tmpContactId = null;
          } else {
            _tmpContactId = _stmt.getText(_columnIndexOfContactId);
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
          _item = new FollowUpEntity(_tmpId,_tmpUserId,_tmpDate,_tmpType,_tmpResponseStatus,_tmpNotes,_tmpCandidatureId,_tmpCompanyId,_tmpContactId,_tmpSyncHash,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<FollowUpEntity> getByIdForUser(final String id, final String userId) {
    final String _sql = "SELECT * FROM relances WHERE id=? AND userId=?";
    return FlowUtil.createFlow(__db, false, new String[] {"relances"}, (_connection) -> {
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
        final int _columnIndexOfDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "date");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final int _columnIndexOfResponseStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "responseStatus");
        final int _columnIndexOfNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notes");
        final int _columnIndexOfCandidatureId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "candidatureId");
        final int _columnIndexOfCompanyId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "companyId");
        final int _columnIndexOfContactId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "contactId");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final FollowUpEntity _result;
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
          final long _tmpDate;
          _tmpDate = _stmt.getLong(_columnIndexOfDate);
          final String _tmpType;
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmpType = null;
          } else {
            _tmpType = _stmt.getText(_columnIndexOfType);
          }
          final String _tmpResponseStatus;
          if (_stmt.isNull(_columnIndexOfResponseStatus)) {
            _tmpResponseStatus = null;
          } else {
            _tmpResponseStatus = _stmt.getText(_columnIndexOfResponseStatus);
          }
          final String _tmpNotes;
          if (_stmt.isNull(_columnIndexOfNotes)) {
            _tmpNotes = null;
          } else {
            _tmpNotes = _stmt.getText(_columnIndexOfNotes);
          }
          final String _tmpCandidatureId;
          if (_stmt.isNull(_columnIndexOfCandidatureId)) {
            _tmpCandidatureId = null;
          } else {
            _tmpCandidatureId = _stmt.getText(_columnIndexOfCandidatureId);
          }
          final String _tmpCompanyId;
          if (_stmt.isNull(_columnIndexOfCompanyId)) {
            _tmpCompanyId = null;
          } else {
            _tmpCompanyId = _stmt.getText(_columnIndexOfCompanyId);
          }
          final String _tmpContactId;
          if (_stmt.isNull(_columnIndexOfContactId)) {
            _tmpContactId = null;
          } else {
            _tmpContactId = _stmt.getText(_columnIndexOfContactId);
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
          _result = new FollowUpEntity(_tmpId,_tmpUserId,_tmpDate,_tmpType,_tmpResponseStatus,_tmpNotes,_tmpCandidatureId,_tmpCompanyId,_tmpContactId,_tmpSyncHash,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
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
    _stringBuilder.append("UPDATE relances SET isArchived=1 WHERE id IN(");
    final int _inputSize = ids.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(") AND userId=");
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
    _stringBuilder.append("UPDATE relances SET isDeleted=1 WHERE id IN(");
    final int _inputSize = ids.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(") AND userId=");
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
    _stringBuilder.append("UPDATE relances SET isDeleted=0 WHERE id IN(");
    final int _inputSize = ids.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(") AND userId=");
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
    _stringBuilder.append("DELETE FROM relances WHERE id IN(");
    final int _inputSize = ids.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(") AND userId=");
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
    final String _sql = "DELETE FROM relances WHERE userId=?";
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

  @Override
  public Object deleteAllForCompany(final String companyId,
      final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM relances WHERE companyId=?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (companyId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, companyId);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object deleteAllForApplication(final String applicationId,
                                        final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM relances WHERE candidatureId=?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (applicationId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, applicationId);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object deleteAllForContact(final String contactId,
      final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM relances WHERE contactId=?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (contactId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, contactId);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Flow<List<FollowUpEntity>> getByDateRange(final SimpleSQLiteQuery query) {
    final RoomRawQuery _rawQuery = RoomSQLiteQuery.copyFrom(query).toRoomRawQuery();
    final String _sql = _rawQuery.getSql();
    return FlowUtil.createFlow(__db, false, new String[] {"relances"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        _rawQuery.getBindingFunction().invoke(_stmt);
        final List<FollowUpEntity> _result = new ArrayList<FollowUpEntity>();
        while (_stmt.step()) {
          final FollowUpEntity _item;
          _item = __entityStatementConverter_comDelhommeJobbingtrackDataLocalEntitiesRelanceEntity(_stmt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public String getTableName() {
    return FollowUpDao.DefaultImpls.getTableName(FollowUpDao_Impl.this);
  }

  @Override
  public String getDateColumn() {
    return FollowUpDao.DefaultImpls.getDateColumn(FollowUpDao_Impl.this);
  }

  @Override
  public Flow<List<FollowUpEntity>> getByDateRangeForUser(final String userId, final long start,
                                                          final long end) {
    return FollowUpDao.DefaultImpls.getByDateRangeForUser(FollowUpDao_Impl.this, userId, start, end);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private FollowUpEntity __entityStatementConverter_comDelhommeJobbingtrackDataLocalEntitiesRelanceEntity(
      @NonNull final SQLiteStatement statement) {
    final FollowUpEntity _entity;
    final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndex(statement, "id");
    final int _columnIndexOfUserId = SQLiteStatementUtil.getColumnIndex(statement, "userId");
    final int _columnIndexOfDate = SQLiteStatementUtil.getColumnIndex(statement, "date");
    final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndex(statement, "type");
    final int _columnIndexOfResponseStatus = SQLiteStatementUtil.getColumnIndex(statement, "responseStatus");
    final int _columnIndexOfNotes = SQLiteStatementUtil.getColumnIndex(statement, "notes");
    final int _columnIndexOfCandidatureId = SQLiteStatementUtil.getColumnIndex(statement, "candidatureId");
    final int _columnIndexOfCompanyId = SQLiteStatementUtil.getColumnIndex(statement, "companyId");
    final int _columnIndexOfContactId = SQLiteStatementUtil.getColumnIndex(statement, "contactId");
    final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndex(statement, "syncHash");
    final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndex(statement, "isArchived");
    final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndex(statement, "isDeleted");
    final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndex(statement, "createdAt");
    final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndex(statement, "updatedAt");
    final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndex(statement, "deletedAt");
    final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndex(statement, "archivedAt");
    final String _tmpId;
    if (_columnIndexOfId == -1) {
      _tmpId = null;
    } else {
      if (statement.isNull(_columnIndexOfId)) {
        _tmpId = null;
      } else {
        _tmpId = statement.getText(_columnIndexOfId);
      }
    }
    final String _tmpUserId;
    if (_columnIndexOfUserId == -1) {
      _tmpUserId = null;
    } else {
      if (statement.isNull(_columnIndexOfUserId)) {
        _tmpUserId = null;
      } else {
        _tmpUserId = statement.getText(_columnIndexOfUserId);
      }
    }
    final long _tmpDate;
    if (_columnIndexOfDate == -1) {
      _tmpDate = 0;
    } else {
      _tmpDate = statement.getLong(_columnIndexOfDate);
    }
    final String _tmpType;
    if (_columnIndexOfType == -1) {
      _tmpType = null;
    } else {
      if (statement.isNull(_columnIndexOfType)) {
        _tmpType = null;
      } else {
        _tmpType = statement.getText(_columnIndexOfType);
      }
    }
    final String _tmpResponseStatus;
    if (_columnIndexOfResponseStatus == -1) {
      _tmpResponseStatus = null;
    } else {
      if (statement.isNull(_columnIndexOfResponseStatus)) {
        _tmpResponseStatus = null;
      } else {
        _tmpResponseStatus = statement.getText(_columnIndexOfResponseStatus);
      }
    }
    final String _tmpNotes;
    if (_columnIndexOfNotes == -1) {
      _tmpNotes = null;
    } else {
      if (statement.isNull(_columnIndexOfNotes)) {
        _tmpNotes = null;
      } else {
        _tmpNotes = statement.getText(_columnIndexOfNotes);
      }
    }
    final String _tmpCandidatureId;
    if (_columnIndexOfCandidatureId == -1) {
      _tmpCandidatureId = null;
    } else {
      if (statement.isNull(_columnIndexOfCandidatureId)) {
        _tmpCandidatureId = null;
      } else {
        _tmpCandidatureId = statement.getText(_columnIndexOfCandidatureId);
      }
    }
    final String _tmpCompanyId;
    if (_columnIndexOfCompanyId == -1) {
      _tmpCompanyId = null;
    } else {
      if (statement.isNull(_columnIndexOfCompanyId)) {
        _tmpCompanyId = null;
      } else {
        _tmpCompanyId = statement.getText(_columnIndexOfCompanyId);
      }
    }
    final String _tmpContactId;
    if (_columnIndexOfContactId == -1) {
      _tmpContactId = null;
    } else {
      if (statement.isNull(_columnIndexOfContactId)) {
        _tmpContactId = null;
      } else {
        _tmpContactId = statement.getText(_columnIndexOfContactId);
      }
    }
    final String _tmpSyncHash;
    if (_columnIndexOfSyncHash == -1) {
      _tmpSyncHash = null;
    } else {
      if (statement.isNull(_columnIndexOfSyncHash)) {
        _tmpSyncHash = null;
      } else {
        _tmpSyncHash = statement.getText(_columnIndexOfSyncHash);
      }
    }
    final boolean _tmpIsArchived;
    if (_columnIndexOfIsArchived == -1) {
      _tmpIsArchived = false;
    } else {
      final int _tmp;
      _tmp = (int) (statement.getLong(_columnIndexOfIsArchived));
      _tmpIsArchived = _tmp != 0;
    }
    final boolean _tmpIsDeleted;
    if (_columnIndexOfIsDeleted == -1) {
      _tmpIsDeleted = false;
    } else {
      final int _tmp_1;
      _tmp_1 = (int) (statement.getLong(_columnIndexOfIsDeleted));
      _tmpIsDeleted = _tmp_1 != 0;
    }
    final long _tmpCreatedAt;
    if (_columnIndexOfCreatedAt == -1) {
      _tmpCreatedAt = 0;
    } else {
      _tmpCreatedAt = statement.getLong(_columnIndexOfCreatedAt);
    }
    final long _tmpUpdatedAt;
    if (_columnIndexOfUpdatedAt == -1) {
      _tmpUpdatedAt = 0;
    } else {
      _tmpUpdatedAt = statement.getLong(_columnIndexOfUpdatedAt);
    }
    final Long _tmpDeletedAt;
    if (_columnIndexOfDeletedAt == -1) {
      _tmpDeletedAt = null;
    } else {
      if (statement.isNull(_columnIndexOfDeletedAt)) {
        _tmpDeletedAt = null;
      } else {
        _tmpDeletedAt = statement.getLong(_columnIndexOfDeletedAt);
      }
    }
    final Long _tmpArchivedAt;
    if (_columnIndexOfArchivedAt == -1) {
      _tmpArchivedAt = null;
    } else {
      if (statement.isNull(_columnIndexOfArchivedAt)) {
        _tmpArchivedAt = null;
      } else {
        _tmpArchivedAt = statement.getLong(_columnIndexOfArchivedAt);
      }
    }
    _entity = new FollowUpEntity(_tmpId,_tmpUserId,_tmpDate,_tmpType,_tmpResponseStatus,_tmpNotes,_tmpCandidatureId,_tmpCompanyId,_tmpContactId,_tmpSyncHash,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
    return _entity;
  }
}
